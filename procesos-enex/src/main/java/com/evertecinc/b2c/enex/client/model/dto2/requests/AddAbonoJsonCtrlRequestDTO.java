package com.evertecinc.b2c.enex.client.model.dto2.requests;

import org.springframework.web.multipart.MultipartFile;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddAbonoJsonCtrlRequestDTO extends CriterioBusquedaGenericoSessionDTO{

	private String asignacion;
	
	private Long monto;
	
	private String numeroOC;
	
	private Long idClient;
	
	private String tipoOperacion;
	
	private MultipartFile documentoOC;
	
	private MultipartFile documentoTributario;
}
