package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.io.Serializable;

@lombok.Data
public class DocumentDTO implements Serializable {

	private static final long serialVersionUID = 251945224269049286L;

	private String globalDocumentId;
	private String date;
	private Integer documentTypeId;
	private String documentTypeName;
	private String seriesNumber;
	private Integer number;
	private String numberStr;
	private String documentSenderCode;
	private String documentSenderName;
	private String documentReceiverCode;
	private String documentReceiverName;

}
