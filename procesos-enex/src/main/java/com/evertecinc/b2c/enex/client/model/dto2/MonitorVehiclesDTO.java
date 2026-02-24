package com.evertecinc.b2c.enex.client.model.dto2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoDTO;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MonitorVehiclesDTO extends CriterioBusquedaGenericoDTO {

	private Long    idVehicle;
	private Long    idClient;
	private String    legalName;
	private Long    idCard;
	private Long    idDepartment;
	private String  productCode;
	private String  vehicleTypeCode;
	private String  plate;
	private String  clientType;
	private String  documentType;
	private String  vehicleStatus;
	private String  nickname;
	private String  warningStockChannel;
	private BigDecimal warningStock;
	private String warningStockCelular;
	private String warningStockMail;
	private String warningLoadChannel;
	private String warningLoadCelular;
	private String warningLoadMail;
	private String warningLoadFailChannel;
	private String warningLoadFailCelular;
	private String warningLoadFailEmail;
	private String reqcardStatus;
	private CardDTO card;
	private String codeOrpakClient;
	private Boolean reqCardReprint;
	private Integer cantidad;
	private String upi;
	private String name;
	private String cardnum;
	private String cardStatus;
	private Boolean controlTotal;
	private Boolean datapass;
	private String ctPosition;
	private String nombreDepto;
	private String tipoVehiculo;
	private String nombreProducto;
	private String	codeTypeVehicle;
	//filtro fecha
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateIn;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSetter(nulls = Nulls.SKIP)
	private LocalDateTime dateEnd;

	/**
	 * @param idVehicle
	 * @param idClient
	 * @param idCard
	 * @param productCode
	 * @param plate
	 * @param clientType
	 * @param documentType
	 * @param vehicleStatus
	 * @param nickname
	 * @param upi
	 * @param name
	 * @param cardnum
	 * @param cardStatus
	 * @param controlTotal
	 * @param datapass
	 * @param ctPosition
	 * @param nombreDepto
	 * @param tipoVehiculo
	 * @param nombreProducto
	 */
	public MonitorVehiclesDTO(Long idVehicle, Long idClient, Long idCard, String productCode, String plate,
			String clientType, String documentType, String vehicleStatus, String nickname, String upi, String name,
			String cardnum, String cardStatus, Boolean controlTotal, Boolean datapass, String ctPosition,
			String nombreDepto, String tipoVehiculo, String nombreProducto) {
		this.idVehicle = idVehicle;
		this.idClient = idClient;
		this.idCard = idCard;
		this.productCode = productCode;
		this.plate = plate;
		this.clientType = clientType;
		this.documentType = documentType;
		this.vehicleStatus = vehicleStatus;
		this.nickname = nickname;
		this.upi = upi;
		this.name = name;
		this.cardnum = cardnum;
		this.cardStatus = cardStatus;
		this.controlTotal = controlTotal;
		this.datapass = datapass;
		this.ctPosition = ctPosition;
		this.nombreDepto = nombreDepto;
		this.tipoVehiculo = tipoVehiculo;
		this.nombreProducto = nombreProducto;
	}
	
	


	
}
