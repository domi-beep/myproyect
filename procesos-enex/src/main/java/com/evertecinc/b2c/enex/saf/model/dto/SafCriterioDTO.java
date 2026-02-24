package com.evertecinc.b2c.enex.saf.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SafCriterioDTO {
    private Long idQueue;
    private String idElement;
    private String type;
    private String status;
    private LocalDateTime dateins;
    private LocalDateTime datesend;
    private Integer numRetries;
    private String data;
    private Integer task;
}
