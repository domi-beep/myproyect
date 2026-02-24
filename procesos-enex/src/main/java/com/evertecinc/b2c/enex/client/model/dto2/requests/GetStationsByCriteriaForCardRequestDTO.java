package com.evertecinc.b2c.enex.client.model.dto2.requests;

import java.util.List;

import lombok.Data;

@Data
public class GetStationsByCriteriaForCardRequestDTO {
	public List<Long> zoneIDs;
	public List<Long> regionIDs;
	public List<Long> areaIDs;
	public String clientType;
	public Long idCard;
	public Boolean controlPass;
	public Long idClient;
}