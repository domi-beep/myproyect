package com.evertecinc.b2c.enex.client.model.dto2.requests;

import org.springframework.web.multipart.MultipartFile;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubirArchivoRequestDTO extends CriterioBusquedaGenericoSessionDTO{
	
	@JsonProperty("file")
	private MultipartFile file; 
	
	@JsonProperty("tipoElemento")
	private String tipoElemento;
	
	@JsonProperty("tipoArchivo")
	private String tipoArchivo; 
	
	@JsonProperty("nombreFile")
	private String nombreFile;  
	
	@JsonProperty("size")
	private String size;        
	
	@JsonProperty("path")
	private String path;        
	
	@JsonProperty("orden")
	private Long orden;      
	
	@JsonProperty("idContent")
	private Long idContent;   

}
