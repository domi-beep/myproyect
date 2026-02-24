package com.evertecinc.b2c.enex.client.model.dto2;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
	
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
