package com.evertecinc.b2c.enex.client.model.dto2;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SugerenciaDTO {

	
	private String mailRecipent;
    private String mailSubject;
    private ContactDTO contact;
    private String mailRecipentName;
    private String portal;
    private String mailFromName;
    private String mailFrom;
    private String url;
	
}
