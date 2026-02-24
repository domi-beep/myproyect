package com.evertecinc.b2c.enex.client.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardHistoryDTO {
    
    private Long idHistory;
    private Long idCard;
    private Long idUser;
    private String username;
    private LocalDateTime date;
    private String action;
    private String actionType;
    private Integer cantidad;
    private String typeBalance;
    private String departamento;
    private String cardStatus;
    private String cardnum;
}
