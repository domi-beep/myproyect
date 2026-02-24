package com.evertecinc.b2c.enex.saf.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "saf_queue")
@Data
//@NoArgsConstructor
public class SafQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_queue")
    private Long idQueue;

    @Column(name = "id_element")
    private String idElement;

    @Column(name = "type")
    private String type;

    @Column(name = "status", nullable = false)
    private String status = "P";

    @Column(name = "dateins")
    private LocalDateTime dateIns;

    @Column(name = "datesend")
    private LocalDateTime dateSend;

    @Column(name = "numretries", nullable = false)
    private int numRetries = 0;

    @Column(name = "data")
    private String data;

    @Column(name = "task")
    private Integer task;
}