package com.evertecinc.b2c.enex.client.model.dto2.requests;

import com.evertecinc.b2c.enex.client.model.dto2.criterio.CriterioBusquedaGenericoSessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdAlertDepartmentJsonCtrlRequestDTO extends CriterioBusquedaGenericoSessionDTO {
	
	@JsonProperty("idDepartment")
	private Long idDepartment;
	
	@JsonProperty("channel")
	private String warningStockChannel;
	
	@JsonProperty("celular_stock")
	private String warningStockCelular;
	
	@JsonProperty("mail_stock")
	private String warningStockMail;
	
	@JsonProperty("monto_stock")
	private String warningStock;
	
	@JsonProperty("channel_carga")
	private String warningLoadChannel;
	
	@JsonProperty("celular_Carga")
	private String warningLoadCelular;
	
	@JsonProperty("mail_Carga")
	private String warningLoadMail;
	
	@JsonProperty("channel_fail")
	private String warningLoadFailChannel;
	
	@JsonProperty("celular_fail")
	private String warningLoadFailCelular;
	
	@JsonProperty("mail_fail")
	private String warningLoadFailEmail;
	
}
