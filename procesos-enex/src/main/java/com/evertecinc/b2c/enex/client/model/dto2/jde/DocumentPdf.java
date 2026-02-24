package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@lombok.Data
public class DocumentPdf implements Serializable {

	private static final long serialVersionUID = -1662934432376309673L;

	@SerializedName("Name")
	@Expose
	private String name;
	@SerializedName("Description")
	@Expose
	private String description;
	@SerializedName("Base64Content")
	@Expose
	private String base64Content;
	@SerializedName("Timestamp")
	@Expose
	private String timestamp;
	@SerializedName("Type")
	@Expose
	private String type;
}
