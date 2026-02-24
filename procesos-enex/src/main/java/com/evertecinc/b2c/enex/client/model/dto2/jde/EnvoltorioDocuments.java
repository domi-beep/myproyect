package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@lombok.Data
public class EnvoltorioDocuments implements Serializable {

	private static final long serialVersionUID = 545802848034936234L;

	@SerializedName("Documents")
	@Expose
	private List<Document> documents;

}
