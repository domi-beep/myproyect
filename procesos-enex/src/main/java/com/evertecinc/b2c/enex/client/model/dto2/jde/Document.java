package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@lombok.Data
public class Document implements Serializable {

	private static final long serialVersionUID = 1083816990338544979L;

	@SerializedName("GlobalDocumentId")
	@Expose
	private String globalDocumentId;
	@SerializedName("CountryId")
	@Expose
	private String countryId;
	@SerializedName("Date")
	@Expose
	private String date;
	@SerializedName("DocumentTypeId")
	@Expose
	private Integer documentTypeId;
	@SerializedName("DocumentTypeName")
	@Expose
	private String documentTypeName;
	@SerializedName("NetAmount")
	@Expose
	private Double netAmount;
	@SerializedName("FreeAmount")
	@Expose
	private Double freeAmount;
	@SerializedName("TaxAmount")
	@Expose
	private Double taxAmount;
	@SerializedName("TotalAmount")
	@Expose
	private Double totalAmount;
	@SerializedName("CurrencyType")
	@Expose
	private String currencyType;
	@SerializedName("SeriesNumber")
	@Expose
	private String seriesNumber;
	@SerializedName("Number")
	@Expose
	private Integer number;
	@SerializedName("NumberStr")
	@Expose
	private String numberStr;
	@SerializedName("DocumentSenderCode")
	@Expose
	private String documentSenderCode;
	@SerializedName("DocumentSenderName")
	@Expose
	private String documentSenderName;
	@SerializedName("DocumentReceiverCode")
	@Expose
	private String documentReceiverCode;
	@SerializedName("DocumentReceiverName")
	@Expose
	private String documentReceiverName;
	@SerializedName("DocumentTimeStamp")
	@Expose
	private String documentTimeStamp;
}
