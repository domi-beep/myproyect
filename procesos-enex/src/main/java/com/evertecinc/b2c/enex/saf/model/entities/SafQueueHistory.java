package com.evertecinc.b2c.enex.saf.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "saf_queue_history")
public class SafQueueHistory implements Serializable {

    private static final long serialVersionUID = 6845352392465135783L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private Long idHistory;

    @Column(name = "id_queue", nullable = false)
    private Long idQueue;

    @Column(name = "dateins")
    private LocalDateTime dateIns;

    @Column(name = "return_code", length = 45)
    private String returnCode;

    @Column(name = "message", length = 100)
    private String message;

    @Column(name = "uri", length = 2000)
    private String uri;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_queue", insertable = false, updatable = false)
    private SafQueue safQueue;
}