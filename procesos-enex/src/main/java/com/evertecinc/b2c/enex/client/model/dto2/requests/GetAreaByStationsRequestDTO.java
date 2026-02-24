package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import lombok.Data;

@Data
public class GetAreaByStationsRequestDTO {
	public List<Long> regiones;
	public String clientType;
	public Long idCard;
	public Long idRegion;
	public Boolean controlPass;
	public Long idClient;

}