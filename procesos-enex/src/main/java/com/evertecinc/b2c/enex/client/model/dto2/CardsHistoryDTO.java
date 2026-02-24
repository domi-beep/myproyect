package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardsHistoryDTO {

	private Long idHistory;
	private Long idCard;
	private Long idUser;
	private String username;
	private LocalDateTime date;
	private String action;
	private String actionType;
	private String name;
	private String typeBalance;
	private String cardStatus;
	private String cardnum;
	
	/**
	 * Constructor para extended de getListCardHistoryByCriteria
	 * @param idHistory
	 * @param idCard
	 * @param idUser
	 * @param username
	 * @param date
	 * @param action
	 * @param actionType
	 * @param name
	 * @param typeBalance
	 * @param cardStatus
	 * @param cardnum
	 */
	public CardsHistoryDTO(Long idHistory, Long idCard, Long idUser, String username, LocalDateTime date, String action,
			String actionType, String name, String typeBalance, String cardStatus, String cardnum) {
		super();
		this.idHistory = idHistory;
		this.idCard = idCard;
		this.idUser = idUser;
		this.username = username;
		this.date = date;
		this.action = action;
		this.actionType = actionType;
		this.name = name;
		this.typeBalance = typeBalance;
		this.cardStatus = cardStatus;
		this.cardnum = cardnum;
	}
	
	

}
