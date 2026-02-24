package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SafQueueDTO {
	
	private Long idQueue;
	private Long idElement;
	private String type;
	private String status;
	private LocalDateTime dateIns;
	private LocalDateTime dateSend;
	private Long numRetries;
	private String data;
	private Long task;

}
