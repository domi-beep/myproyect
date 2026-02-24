package com.evertecinc.b2c.enex.client.model.dto2;

import java.io.Serializable;

import lombok.Data;

@Data
public class SimpleQMail implements Serializable{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1L;
	private Long mailId;
	private String mailFrom;
	private String mailFromName;
	private String mailRecipent;
	private String mailRecipentName;
	private String mailSubject;
	private String mailBody;
	private String attachFile;
	
}