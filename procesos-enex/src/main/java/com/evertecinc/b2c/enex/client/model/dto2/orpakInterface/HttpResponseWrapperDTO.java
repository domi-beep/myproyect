package com.evertecinc.b2c.enex.client.model.dto2.orpakInterface;

import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class HttpResponseWrapperDTO {
	
	private final String body;
    private final int statusCode;

}
