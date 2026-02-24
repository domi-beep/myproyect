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
public class DepartmentHistoryDTO {

    private Long idHistory;

    private Long idDepartment;

    private Long idUser;

    private String username;

    private LocalDateTime date;

    private String action;

    private String actionType;
}

