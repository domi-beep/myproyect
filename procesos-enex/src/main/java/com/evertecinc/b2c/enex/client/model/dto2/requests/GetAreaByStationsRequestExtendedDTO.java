package com.evertecinc.b2c.enex.client.model.dto2.requests;

import lombok.Data;

@Data
public class GetAreaByStationsRequestExtendedDTO {
	public String clientType;
	public Long idCard;
	public Long idRegion;
	public Boolean controlPass;
	public Long idClient;

}