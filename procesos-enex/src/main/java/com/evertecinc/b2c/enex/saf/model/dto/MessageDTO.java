package com.evertecinc.b2c.enex.saf.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
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
