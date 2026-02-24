package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PreciosLitrosHistoryDTO {
	private Long idHistory;
	private Long idPriceLiter;
	private String username;
	private LocalDateTime date;
	private String action;
	private String actionType;
	
	
	public PreciosLitrosHistoryDTO(Long idHistory, Long idPriceLiter, String username, LocalDateTime date,
			String action, String actionType) {
		super();
		this.idHistory = idHistory;
		this.idPriceLiter = idPriceLiter;
		this.username = username;
		this.date = date;
		this.action = action;
		this.actionType = actionType;
	}


	public PreciosLitrosHistoryDTO() {
	}
}
