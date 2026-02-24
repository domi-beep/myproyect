package com.evertecinc.b2c.enex.client.model.dto2;

import java.util.Date;

import lombok.Data;

@Data
public class AddMailQueueDTO extends SimpleQMail {
	private static final long serialVersionUID = 1L;

	private Date mailQueuedDate;
	
}