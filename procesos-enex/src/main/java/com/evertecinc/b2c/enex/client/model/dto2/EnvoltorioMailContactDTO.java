package com.evertecinc.b2c.enex.client.model.dto2;


import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class EnvoltorioMailContactDTO {
	
	 @SerializedName("mailRecipent")
	    private String mailRecipent;

	    @SerializedName("mailSubject")
	    private String mailSubject;

	    @SerializedName("contact")
	    private ContactDTO contact;

	    @SerializedName("mailRecipentName")
	    private String mailRecipentName;

	    @SerializedName("portal")
	    private String portal;

	    @SerializedName("mailFromName")
	    private String mailFromName;

	    @SerializedName("mailFrom")
	    private String mailFrom;

	    @SerializedName("url")
	    private String url;

}
