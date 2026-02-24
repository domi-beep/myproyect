package com.evertecinc.b2c.enex.integracion.service;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.evertecinc.b2c.enex.client.constants.OrpakRestClientConstants;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CardConstraintRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CardConstraintResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CreateDepartmentRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.CreateDepartmentResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpak.GetDepartmentBalanceResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.CreateVehicleCardRequest;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.CreateVehicleCardResponse;
import com.evertecinc.b2c.enex.client.model.dto2.orpakInterface.GetDepartmentBalanceRequest;
import com.evertecinc.b2c.enex.client.model.dto2.restResponses.UpdateCustomerBalanceResponse;
import com.evertecinc.b2c.enex.integracion.constants.OrpakWSResponseConstants;
import com.evertecinc.b2c.enex.integracion.exception.OrpakException;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeCardStatusRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeCardStatusResponse;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.ChangeDepartmentStatusResponse;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateCustomerContractRequest;
import com.evertecinc.b2c.enex.integracion.model.dto.CreateCustomerContractResponse;
import com.evertecinc.b2c.enex.model.dto.request.UpdateCustomerBalanceRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Service("OrpakRestClientServiceImpl")
@RequiredArgsConstructor
//@ConfigurationProperties(prefix = "orpak.wsrest.config")
@Slf4j
public class OrpakRestClientServiceImpl implements OrpakRestClientService {
	
	
    @Value("${orpak.wsrest.config.host}")
    private String host;

    @Value("${orpak.wsrest.config.path}")
    private String path;

	@Override
	public ChangeCardStatusResponse changeCardStatus(ChangeCardStatusRequest ccsr) throws OrpakException {
		
		ChangeCardStatusResponse response = new ChangeCardStatusResponse();

	    WebClient webClient = WebClient.builder()
	            .baseUrl(host + path.concat(OrpakRestClientConstants.WS_CHANGE_CARD_STATUS))
	            .defaultHeader("Content-Type", "application/json")
	            .build();

	    try {
	    	String uriString = host + path.concat(OrpakRestClientConstants.WS_CHANGE_CARD_STATUS) + "?" +
                    "cardnumber=" + URLEncoder.encode(ccsr.getCardnumber(), StandardCharsets.UTF_8) + "&" +
                    "sender=" + URLEncoder.encode(ccsr.getSender(), StandardCharsets.UTF_8) + "&" +
                    "status=" + URLEncoder.encode(ccsr.getStatus(), StandardCharsets.UTF_8);

            URI uri = new URI(uriString);

	        log.info("URI: " + uri);

	     // Obtener la respuesta como String
	        String jsonResponse = webClient.get()
	                .uri(uriBuilder -> uriBuilder
	                        .queryParam("cardnumber", ccsr.getCardnumber())
	                        .queryParam("sender", ccsr.getSender())
	                        .queryParam("status", ccsr.getStatus())
	                        .build())
	                .retrieve()
	                .onStatus(
	                        status -> status.isError(),
	                        clientResponse -> clientResponse.bodyToMono(String.class)
	                                .flatMap(errorBody -> Mono.error(new OrpakException("Error: " + errorBody, log)))
	                )
	                .bodyToMono(String.class)
	                .block();

	        log.info("Respuesta JSON:\n" + jsonResponse);

	        // Convertir la respuesta JSON a ChangeCardStatusResponse
	        response.setReturnCode(Integer.valueOf(jsonResponse));
	        response.setUri(uriString);
	        response.setMessage("");


	        log.info("Respuesta 2.4:\n" + response);

	        return response;
	    } catch (UnsupportedCharsetException | URISyntaxException e) {
            throw new OrpakException(e, log);
        } catch (WebClientResponseException e) {
            log.error("Error en la llamada al servicio REST: " + e.getResponseBodyAsString(), e);
            throw new OrpakException("Error en la llamada al servicio REST", e);
        } catch (Exception e) {
            log.error("Error inesperado en la llamada al servicio REST", e);
            throw new OrpakException("Error inesperado en la llamada al servicio REST", e);
        }
		
	}

	@Override
	public CreateCustomerContractResponse createClientContract(CreateCustomerContractRequest customerContractRequest) {
		
		CreateCustomerContractResponse response = new CreateCustomerContractResponse();

        WebClient webClient = WebClient.builder()
                .baseUrl(host + path.concat(OrpakRestClientConstants.WS_CREATE_CLIENT_CONTRACT))
                .defaultHeader("Content-Type", "application/json")
                .build();

        try {
            String uriString = host + path.concat(OrpakRestClientConstants.WS_CREATE_CLIENT_CONTRACT) + "?" +
                    "operationtype=" + URLEncoder.encode(String.valueOf(customerContractRequest.getOperationtype()), StandardCharsets.UTF_8) + "&" +
                    "customercode=" + URLEncoder.encode(customerContractRequest.getCustomercode(), StandardCharsets.UTF_8) + "&" +
                    "customerdescription=" + URLEncoder.encode(customerContractRequest.getCustomerdescription(), StandardCharsets.UTF_8) + "&" +
                    "status=" + URLEncoder.encode(customerContractRequest.getStatus(), StandardCharsets.UTF_8) + "&" +
                    "alternativecode=" + URLEncoder.encode(customerContractRequest.getAlternativecode(), StandardCharsets.UTF_8) + "&" +
                    "address1=" + URLEncoder.encode(customerContractRequest.getAddress1(), StandardCharsets.UTF_8) + "&" +
                    "address2=" + URLEncoder.encode(customerContractRequest.getAddress2(), StandardCharsets.UTF_8) + "&" +
                    "disctrict=" + URLEncoder.encode(customerContractRequest.getDisctrict(), StandardCharsets.UTF_8) + "&" +
                    "city=" + URLEncoder.encode(customerContractRequest.getCity(), StandardCharsets.UTF_8) + "&" +
                    "zip=" + URLEncoder.encode(customerContractRequest.getZip(), StandardCharsets.UTF_8) + "&" +
                    "phone=" + URLEncoder.encode(customerContractRequest.getPhone(), StandardCharsets.UTF_8) + "&" +
                    "fax=" + URLEncoder.encode(customerContractRequest.getFax(), StandardCharsets.UTF_8) + "&" +
                    "email=" + URLEncoder.encode(customerContractRequest.getEmail(), StandardCharsets.UTF_8) + "&" +
                    "rut=" + URLEncoder.encode(customerContractRequest.getRut(), StandardCharsets.UTF_8) + "&" +
                    "giro=" + URLEncoder.encode(customerContractRequest.getGiro(), StandardCharsets.UTF_8) + "&" +
                    "contactname=" + URLEncoder.encode(customerContractRequest.getContactname(), StandardCharsets.UTF_8) + "&" +
                    "creditlimit=" + URLEncoder.encode(customerContractRequest.getCreditlimit() != null ? customerContractRequest.getCreditlimit() : "", StandardCharsets.UTF_8) + "&" +
                    "security=" + URLEncoder.encode(customerContractRequest.getSecurity(), StandardCharsets.UTF_8) + "&" +
                    "warninglevel=" + URLEncoder.encode(customerContractRequest.getWarninglevel(), StandardCharsets.UTF_8) + "&" +
                    "contractstartdate=" + URLEncoder.encode(customerContractRequest.getContractstartdate(), StandardCharsets.UTF_8) + "&" +
                    "contractenddate=" + URLEncoder.encode(customerContractRequest.getContractenddate(), StandardCharsets.UTF_8);

            URI uri = new URI(uriString);

            log.info("URI: " + uri);

            String responseBody = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("operationtype", customerContractRequest.getOperationtype())
                            .queryParam("customercode", customerContractRequest.getCustomercode())
                            .queryParam("customerdescription", customerContractRequest.getCustomerdescription())
                            .queryParam("status", customerContractRequest.getStatus())
                            .queryParam("alternativecode", customerContractRequest.getAlternativecode())
                            .queryParam("address1", customerContractRequest.getAddress1())
                            .queryParam("address2", customerContractRequest.getAddress2())
                            .queryParam("disctrict", customerContractRequest.getDisctrict())
                            .queryParam("city", customerContractRequest.getCity())
                            .queryParam("zip", customerContractRequest.getZip())
                            .queryParam("phone", customerContractRequest.getPhone())
                            .queryParam("fax", customerContractRequest.getFax())
                            .queryParam("email", customerContractRequest.getEmail())
                            .queryParam("rut", customerContractRequest.getRut())
                            .queryParam("giro", customerContractRequest.getGiro())
                            .queryParam("contactname", customerContractRequest.getContactname())
                            .queryParam("creditlimit", customerContractRequest.getCreditlimit())
                            .queryParam("security", customerContractRequest.getSecurity())
                            .queryParam("warninglevel", customerContractRequest.getWarninglevel())
                            .queryParam("contractstartdate", customerContractRequest.getContractstartdate())
                            .queryParam("contractenddate", customerContractRequest.getContractenddate())
                            .build())
                    .retrieve()
                    .onStatus(
                            status -> status.isError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .flatMap(errorBody -> Mono.error(new OrpakException("Error: " + errorBody, log)))
                    )
                    .bodyToMono(String.class)
                    .block();

            log.info("Respuesta 2.1:\n" + responseBody);

            response.setReturnCode(Integer.parseInt(responseBody));
            response.setUri(uriString);
            response.setMessage(responseBody.concat(" : ").concat(this.getMensajeUcoPorCodigo(responseBody)));
            
            log.info("Respuesta 2.1:\n" + response);
            return response;

        } catch (UnsupportedCharsetException | URISyntaxException e) {
            throw new OrpakException(e, log);
        } catch (WebClientResponseException e) {
            log.error("Error en la llamada al servicio REST: " + e.getResponseBodyAsString(), e);
            throw new OrpakException("Error en la llamada al servicio REST", e);
        } catch (Exception e) {
            log.error("Error inesperado en la llamada al servicio REST", e);
            throw new OrpakException("Error inesperado en la llamada al servicio REST", e);
        }
	}
	
	public static String getMensajeUcoPorCodigo(String codigo) {
        // Limpia el código de posibles caracteres extraños (ej: ".-2", "-1")
        String codigoLimpio = codigo.replaceAll("[^\\d]", ""); // deja solo dígitos

        String nombreConstante = "PROCESO_UCO_" + codigoLimpio;

        try {
            Field field = OrpakWSResponseConstants.class.getDeclaredField(nombreConstante);
            return (String) field.get(null); // null porque es campo estático
        } catch (NoSuchFieldException e) {
            return "Código de error desconocido: " + codigo;
        } catch (IllegalAccessException e) {
            return "Error al acceder al mensaje del código: " + codigo;
        }
    }

	@Override
	public ChangeDepartmentStatusResponse changeDepartmentStatus(ChangeDepartmentStatusRequest request) {

	    ChangeDepartmentStatusResponse response = new ChangeDepartmentStatusResponse();

	    // Construimos el WebClient base
	    WebClient webClient = WebClient.builder()
	            .baseUrl(host + path.concat(OrpakRestClientConstants.WS_CHANGE_DEPARTMENT_STATUS))
	            .defaultHeader("Content-Type", "application/json")
	            .build();

	    try {
	        // (Opcional) Construimos un String con la URI para loguearlo
	        String uriString = host
	                + path.concat(OrpakRestClientConstants.WS_CHANGE_DEPARTMENT_STATUS)
	                + "?customercode=" + URLEncoder.encode(request.getCustomercode(), StandardCharsets.UTF_8)
	                + "&fleetdepartment=" + URLEncoder.encode(request.getFleetdepartment(), StandardCharsets.UTF_8)
	                + "&status=" + URLEncoder.encode(request.getStatus(), StandardCharsets.UTF_8);

	        log.info("URI: " + uriString);

	        // Invocamos el servicio usando GET y pasamos los queryParams con uriBuilder
	        String responseBody = webClient.get()
	                .uri(uriBuilder -> uriBuilder
	                        .queryParam("customercode", request.getCustomercode())
	                        .queryParam("fleetdepartment", request.getFleetdepartment())
	                        .queryParam("status", request.getStatus())
	                        .build()
	                )
	                .retrieve()
	                .onStatus(
	                        status -> status.isError(),
	                        clientResponse -> clientResponse.bodyToMono(String.class)
	                                .flatMap(errorBody -> Mono.error(new OrpakException("Error: " + errorBody, log)))
	                )
	                // Aquí tomamos la respuesta tal cual (String); ajusta a tu formato si el servicio entrega JSON.
	                .bodyToMono(String.class)
	                .block();

	        // Convertimos el cuerpo de la respuesta a int y lo seteamos como returnCode;
	        // ajusta 'message' si el servicio retorna algún texto descriptivo.
	        response.setReturnCode(Integer.parseInt(responseBody));
	        response.setMessage("Operación completada"); 
	        response.setUri(uriString);

	        log.info("Respuesta 2.7:\n" + responseBody);

	        return response;

	    } catch (UnsupportedCharsetException e) {
	        throw new OrpakException(e, log);
	    } catch (WebClientResponseException e) {
	        log.error("Error en la llamada al servicio REST: " + e.getResponseBodyAsString(), e);
	        throw new OrpakException("Error en la llamada al servicio REST", e);
	    } catch (Exception e) {
	        log.error("Error inesperado en la llamada al servicio REST", e);
	        throw new OrpakException("Error inesperado en la llamada al servicio REST", e);
	    }
	}

	@Override
	public CreateVehicleCardResponse createVehicleCard(CreateVehicleCardRequest request) {

	    CreateVehicleCardResponse response = new CreateVehicleCardResponse();

	    // Construimos el WebClient base
	    WebClient webClient = WebClient.builder()
	            .baseUrl(host + path.concat(OrpakRestClientConstants.WS_CREATE_VEHICLE_CARD))
	            .defaultHeader("Content-Type", "application/json")
	            .build();

	    try {
	        // 1) Construimos un String con la URI (para logging, opcional)
	        String uriString = host
	                + path.concat(OrpakRestClientConstants.WS_CREATE_VEHICLE_CARD)
	                + "?operationtype=" + URLEncoder.encode(String.valueOf(request.getOperationtype()), StandardCharsets.UTF_8)
	                + "&customercode=" + URLEncoder.encode(request.getCustomercode(), StandardCharsets.UTF_8)
	                + "&plate=" + URLEncoder.encode(request.getPlate(), StandardCharsets.UTF_8)
	                + "&name=" + URLEncoder.encode(request.getName(), StandardCharsets.UTF_8)
	                + "&surname=" + URLEncoder.encode(request.getSurname(), StandardCharsets.UTF_8)
	                + "&departmentcode=" + (request.getDepatmentcode() != null
	                ? URLEncoder.encode(request.getDepatmentcode(), StandardCharsets.UTF_8): "")
	                + "&cardnumber=" + URLEncoder.encode(request.getCardnumber(), StandardCharsets.UTF_8)
	                + "&fueltype=" + URLEncoder.encode(request.getFueltype(), StandardCharsets.UTF_8)
	                + "&fuelcode=" + URLEncoder.encode(request.getFuelcode(), StandardCharsets.UTF_8)
	                + "&plafond=" + URLEncoder.encode(String.valueOf(request.getPlafond()), StandardCharsets.UTF_8)
	                + "&balancetype=" + URLEncoder.encode(request.getBalancetype(), StandardCharsets.UTF_8)
	                + "&cardtype=" + URLEncoder.encode(request.getCardtype(), StandardCharsets.UTF_8)
	                + "&doctype=" + URLEncoder.encode(request.getDoctype(), StandardCharsets.UTF_8)
	                + "&vehiclerule=" + URLEncoder.encode(request.getVehiclerule(), StandardCharsets.UTF_8)
	                + "&deviceusage=" + URLEncoder.encode(request.getDeviceusage(), StandardCharsets.UTF_8);

	        log.info("URI: " + uriString);

	        // 2) Ejecutamos la petición GET y pasamos los queryParams al uriBuilder
	        String responseBody = webClient.get()
	                .uri(uriBuilder -> uriBuilder
	                        .queryParam("operationtype", request.getOperationtype())
	                        .queryParam("customercode", request.getCustomercode())
	                        .queryParam("plate", request.getPlate())
	                        .queryParam("name", request.getName())
	                        .queryParam("surname", request.getSurname())
	                        .queryParam("departmentcode", request.getDepatmentcode())
	                        .queryParam("cardnumber", request.getCardnumber())
	                        .queryParam("fueltype", request.getFueltype())
	                        .queryParam("fuelcode", request.getFuelcode())
	                        .queryParam("plafond", request.getPlafond())
	                        .queryParam("balancetype", request.getBalancetype())
	                        .queryParam("cardtype", request.getCardtype())
	                        .queryParam("doctype", request.getDoctype())
	                        .queryParam("vehiclerule", request.getVehiclerule())
	                        .queryParam("deviceusage", request.getDeviceusage())
	                        .build()
	                )
	                .retrieve()
	                .onStatus(
	                        status -> status.isError(),
	                        clientResponse -> clientResponse.bodyToMono(String.class)
	                                .flatMap(errorBody -> Mono.error(new OrpakException("Error: " + errorBody, log)))
	                )
	                // Tomamos la respuesta como String y luego convertimos a int
	                .bodyToMono(String.class)
	                .block();

	        // 3) Seteamos el returnCode y la uri
	        response.setReturnCode(Integer.parseInt(responseBody));
	        response.setUri(uriString);

	        log.info("Respuesta 2.3:\n" + responseBody);

	        return response;

	    } catch (UnsupportedCharsetException e) {
	        throw new OrpakException(e, log);
	    } catch (WebClientResponseException e) {
	        log.error("Error en la llamada al servicio REST: " + e.getResponseBodyAsString(), e);
	        throw new OrpakException("Error en la llamada al servicio REST", e);
	    } catch (Exception e) {
	        log.error("Error inesperado en la llamada al servicio REST", e);
	        throw new OrpakException("Error inesperado en la llamada al servicio REST", e);
	    }
	}


	@Override
	public List<GetDepartmentBalanceResponse> getDepartmentBalance(GetDepartmentBalanceRequest request) {

	    // Lista donde almacenaremos los resultados parseados desde la respuesta
	    List<GetDepartmentBalanceResponse> resultList = new ArrayList<>();

	    // 1) Construimos el WebClient base
	    WebClient webClient = WebClient.builder()
	            .baseUrl(host + path.concat(OrpakRestClientConstants.WS_GET_DEPARTMENT_BALANCE))
	            .defaultHeader("Content-Type", "application/json")
	            .build();

	    try {
	        // 2) (Opcional) Construimos un String con la URI para loguearlo o depurar
	        String uriString = host 
	                + path.concat(OrpakRestClientConstants.WS_GET_DEPARTMENT_BALANCE)
	                + "?departmentid=" + URLEncoder.encode(request.getDepartmentid(), StandardCharsets.UTF_8)
	                + "&balancetype=" + URLEncoder.encode(request.getBalanceType(), StandardCharsets.UTF_8);

	        log.info("URI: " + uriString);

	        // 3) Invocamos el servicio usando GET y pasamos los queryParams con uriBuilder
	        String responseBody = webClient.get()
	                .uri(uriBuilder -> uriBuilder
	                        .queryParam("departmentid", request.getDepartmentid())
	                        .queryParam("balancetype", request.getBalanceType())
	                        .build())
	                .accept(MediaType.APPLICATION_XML) // Indicamos que aceptamos XML
	                .retrieve()
	                .onStatus(
	                        status -> status.isError(),
	                        clientResponse -> clientResponse.bodyToMono(String.class)
	                                .flatMap(errorBody -> Mono.error(new OrpakException("Error: " + errorBody, log)))
	                )
	                // Aquí tomamos la respuesta como String
	                .bodyToMono(String.class)
	                .block();

	        // 4) Parseamos la respuesta XML
	        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        InputSource is = new InputSource(new StringReader(responseBody));
	        Document doc = db.parse(is);

	        // Nos basamos en la etiqueta "d2p1:anyType" para recorrer los nodos
	        NodeList nodes = doc.getElementsByTagName("d2p1:anyType");

	        for (int i = 0; i < nodes.getLength(); i++) {
	            Element element = (Element) nodes.item(i);

	            GetDepartmentBalanceResponse gdbre = new GetDepartmentBalanceResponse();
	            gdbre.setUri(uriString);

	            // Extraemos cada campo con su etiqueta respectiva
	            gdbre.setBalance(
	                    Float.parseFloat(getCharacterDataFromElement(element, "d3p1:Balance").trim())
	            );
	            gdbre.setBalancetype(
	                    getCharacterDataFromElement(element, "d3p1:BalanceType").trim()
	            );
	            gdbre.setCode(
	                    getCharacterDataFromElement(element, "d3p1:Code").trim()
	            );
	            gdbre.setDescription(
	                    getCharacterDataFromElement(element, "d3p1:Description").trim()
	            );
	            gdbre.setStockType(
	                    getCharacterDataFromElement(element, "d3p1:StockType").trim()
	            );
	            gdbre.setReturnCode(
	                    Integer.parseInt(getCharacterDataFromElement(element, "d3p1:result").trim())
	            );

	            resultList.add(gdbre);
	        }

	        log.info("Respuesta 2.13:\n" + responseBody);

	        return resultList;

	    } catch (UnsupportedCharsetException e) {
	        throw new OrpakException(e, log);
	    } catch (WebClientResponseException e) {
	        log.error("Error en la llamada al servicio REST: " + e.getResponseBodyAsString(), e);
	        throw new OrpakException("Error en la llamada al servicio REST", e);
	    } catch (Exception e) {
	        log.error("Error inesperado en la llamada al servicio REST", e);
	        throw new OrpakException("Error inesperado en la llamada al servicio REST", e);
	    }
	}
	
	private String getCharacterDataFromElement(Element parent, String tagName) {
	    NodeList nodeList = parent.getElementsByTagName(tagName);
	    if (nodeList.getLength() == 0) {
	        return "";
	    }
	    Element element = (Element) nodeList.item(0);
	    if (element == null || element.getFirstChild() == null) {
	        return "";
	    }
	    return element.getFirstChild().getNodeValue();
	}

	@Override
	public CreateDepartmentResponse createDepartment(CreateDepartmentRequest cdr) {
	    
	    CreateDepartmentResponse response = new CreateDepartmentResponse();

	    WebClient webClient = WebClient.builder()
	            .baseUrl(host + path.concat(OrpakRestClientConstants.WS_CREATE_DEPARTMENT))
	            .defaultHeader("Content-Type", "application/json")
	            .build();

	    try {
	        String uriString = host + path.concat(OrpakRestClientConstants.WS_CREATE_DEPARTMENT) + "?" +
	                "operationtype=" + URLEncoder.encode(cdr.getOperationtype(), StandardCharsets.UTF_8) + "&" +
	                "customercode=" + URLEncoder.encode(cdr.getCustomercode(), StandardCharsets.UTF_8) + "&" +
	                "departmentdescription=" + URLEncoder.encode(cdr.getDepartmentdescription(), StandardCharsets.UTF_8) + "&" +
	                "newdepartmentdescription=" + URLEncoder.encode(cdr.getNewdepartmentdescription(), StandardCharsets.UTF_8);

	        URI uri = new URI(uriString);

	        log.info("URI: " + uri);

	        String responseBody = webClient.get()
	                .uri(uriBuilder -> uriBuilder
	                        .queryParam("operationtype", cdr.getOperationtype())
	                        .queryParam("customercode", cdr.getCustomercode())
	                        .queryParam("departmentdescription", cdr.getDepartmentdescription())
	                        .queryParam("newdepartmentdescription", cdr.getNewdepartmentdescription())
	                        .build())
	                .retrieve()
	                .onStatus(
	                        status -> status.isError(),
	                        clientResponse -> clientResponse.bodyToMono(String.class)
	                                .flatMap(errorBody -> Mono.error(new OrpakException("Error: " + errorBody, log)))
	                )
	                .bodyToMono(String.class)
	                .block();

	        log.info("Respuesta 2.6:\n" + responseBody);

	        response.setReturnCode(Integer.parseInt(responseBody));
	        response.setUri(uriString);
	        response.setMessage(responseBody.concat(" : ").concat(this.getMensajeUSDPorCodigo(responseBody)));

	        log.info("Respuesta 2.6:\n" + response);
	        return response;

	    } catch (UnsupportedCharsetException | URISyntaxException e) {
	        throw new OrpakException(e, log);
	    } catch (WebClientResponseException e) {
	    	log.error("Error en la llamada al servicio REST: " + e.getResponseBodyAsString(), e);
	        throw new OrpakException("Error en la llamada al servicio REST", e);
	    } catch (Exception e) {
	    	log.error("Error inesperado en la llamada al servicio REST", e);
	        throw new OrpakException("Error inesperado en la llamada al servicio REST", e);
	    }
	}
	
	
	
	public static String getMensajeUSDPorCodigo(String codigo) {
        // Limpia el código de posibles caracteres extraños (ej: ".-2", "-1")
        String codigoLimpio = codigo.replaceAll("[^\\d]", ""); // deja solo dígitos

        String nombreConstante = "PROCESO_USD_" + codigoLimpio;

        try {
            Field field = OrpakWSResponseConstants.class.getDeclaredField(nombreConstante);
            return (String) field.get(null); // null porque es campo estático
        } catch (NoSuchFieldException e) {
            return "Código de error desconocido: " + codigo;
        } catch (IllegalAccessException e) {
            return "Error al acceder al mensaje del código: " + codigo;
        }
    }

	@Override
	public CardConstraintResponse cardConstraint(CardConstraintRequest ccr) throws OrpakException {
	    CardConstraintResponse response = new CardConstraintResponse();

	    String timeList = ccr.getTimeList().stream()
	        .map(t -> String.valueOf(t.getDay()) + "*" + t.getFromtime().trim() + "*" + t.getTotime().trim())
	        .collect(Collectors.joining("*"));

	    String storeList = ccr.getStoreList().stream()
	        .map(s -> s.getStoreCode().trim())
	        .collect(Collectors.joining("*"));

	    String limitList = ccr.getLimit().stream()
	        .map(l -> l.getDurationtype() + "*" + l.getDuration() + "*" + l.getValuetype() + "*" + l.getValue())
	        .collect(Collectors.joining("*"));

	    try {
	        String uriString = host + path.concat(OrpakRestClientConstants.WS_CARD_CONSTRAINT) + "?" +
	                "operationtype=" + URLEncoder.encode(ccr.getOperationtype()+"", StandardCharsets.UTF_8) + "&" +
	                "customercode=" + URLEncoder.encode(ccr.getCustomercode(), StandardCharsets.UTF_8) + "&" +
	                "plate=" + URLEncoder.encode(ccr.getPlate(), StandardCharsets.UTF_8) + "&" +
	                "time=" + URLEncoder.encode(timeList, StandardCharsets.UTF_8) + "&" +
	                "store=" + URLEncoder.encode(storeList, StandardCharsets.UTF_8) + "&" +
	                "limit=" + URLEncoder.encode(limitList, StandardCharsets.UTF_8) + "&" +
	                "itemlist=" + URLEncoder.encode(ccr.getItemlist(), StandardCharsets.UTF_8) + "&" +
	                "storelist=" + URLEncoder.encode(ccr.getStorelist(), StandardCharsets.UTF_8);

	        URI uri = new URI(uriString);
	        log.info("URI 2.9: {}", uri);

	        WebClient webClient = WebClient.builder()
	            .baseUrl(host + path.concat(OrpakRestClientConstants.WS_CARD_CONSTRAINT))
	            .defaultHeader("Content-Type", "application/json")
	            .build();

	        String responseBody = webClient.get()
	            .uri(uriBuilder -> uriBuilder
	                .queryParam("operationtype", ccr.getOperationtype())
	                .queryParam("customercode", ccr.getCustomercode())
	                .queryParam("plate", ccr.getPlate())
	                .queryParam("time", timeList)
	                .queryParam("store", storeList)
	                .queryParam("limit", limitList)
	                .queryParam("itemlist", ccr.getItemlist())
	                .queryParam("storelist", ccr.getStorelist())
	                .build())
	            .retrieve()
	            .onStatus(
	                status -> status.isError(),
	                clientResponse -> clientResponse.bodyToMono(String.class)
	                    .flatMap(errorBody -> Mono.error(new OrpakException("Error: " + errorBody, log)))
	            )
	            .bodyToMono(String.class)
	            .block();

	        log.info("Respuesta 2.9:\n{}", responseBody);

	        response.setReturnCode(Integer.parseInt(responseBody.trim()));
	        response.setUri(uriString);
	        response.setMessage(responseBody.concat(" : ").concat(this.getMensajeUSDPorCodigo(responseBody)));

	        return response;

	    } catch (UnsupportedCharsetException | URISyntaxException e) {
	        throw new OrpakException(e, log);
	    } catch (WebClientResponseException e) {
	        log.error("Error en la llamada al servicio REST: {}", e.getResponseBodyAsString(), e);
	        throw new OrpakException("Error en la llamada al servicio REST", e);
	    } catch (Exception e) {
	        log.error("Error inesperado en la llamada al servicio REST", e);
	        throw new OrpakException("Error inesperado en la llamada al servicio REST", e);
	    }
	}

	@Override
	public UpdateCustomerBalanceResponse updateCustomerBalance(UpdateCustomerBalanceRequest requerimiento) throws OrpakException {
	    UpdateCustomerBalanceResponse retorno = new UpdateCustomerBalanceResponse();

	    try {
	        String uriString = host + path.concat(OrpakRestClientConstants.WS_UPDATE_CUSTOMER_BALANCE) + "?" +
	            "customercode=" + URLEncoder.encode(requerimiento.getCustomerCode(), StandardCharsets.UTF_8) + "&" +
	            "paymentdate=" + URLEncoder.encode(requerimiento.getDate(), StandardCharsets.UTF_8) + "&" +
	            "amount=" + URLEncoder.encode(requerimiento.getAmount().toPlainString().replace(".", ","), StandardCharsets.UTF_8) + "&" +
	            "remarks=" + URLEncoder.encode(requerimiento.getRemarks(), StandardCharsets.UTF_8) + "&" +
	            "transactionid=" + URLEncoder.encode(requerimiento.getTransactionId(), StandardCharsets.UTF_8);

	        URI uri = new URI(uriString);
	        log.info("URI: {}", uri);

	        WebClient webClient = WebClient.builder()
	            .baseUrl(host + path.concat(OrpakRestClientConstants.WS_UPDATE_CUSTOMER_BALANCE))
	            .defaultHeader("Content-Type", "application/json")
	            .build();

	        String responseBody = webClient.get()
	            .uri(uriBuilder -> uriBuilder
	                .queryParam("customercode", requerimiento.getCustomerCode())
	                .queryParam("paymentdate", requerimiento.getDate())
	                .queryParam("amount", requerimiento.getAmount().toPlainString().replace(".", ","))
	                .queryParam("remarks", requerimiento.getRemarks())
	                .queryParam("transactionid", requerimiento.getTransactionId())
	                .build())
	            .retrieve()
	            .onStatus(
	                status -> status.isError(),
	                clientResponse -> clientResponse.bodyToMono(String.class)
	                    .flatMap(errorBody -> Mono.error(new OrpakException("Error: " + errorBody, log)))
	            )
	            .bodyToMono(String.class)
	            .block();

	        log.info("Respuesta {}:\n{}", OrpakRestClientConstants.WS_UPDATE_CUSTOMER_BALANCE, responseBody);

	        String result = responseBody.contains("\"") ? responseBody.replaceAll("\"", "") : responseBody;
	        retorno.setReturnCode(result.trim());
	        retorno.setUri(uriString);
	        return retorno;

	    } catch (UnsupportedCharsetException | URISyntaxException e) {
	        throw new OrpakException(e, log);
	    } catch (WebClientResponseException e) {
	        log.error("Error en la llamada al servicio REST: {}", e.getResponseBodyAsString(), e);
	        throw new OrpakException("Error en la llamada al servicio REST", e);
	    } catch (Exception e) {
	        log.error("Error inesperado en la llamada al servicio REST", e);
	        throw new OrpakException("Error inesperado en la llamada al servicio REST", e);
	    }
	}




}
