package com.evertecinc.b2c.enex.email.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mail_queue", indexes = {@Index(name = "mail_queue_maq_status_idx", columnList = "maq_status")})
public class MailQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maq_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "maq_queued_date")
    private LocalDateTime queuedDate;

    @Column(name = "maq_status", length = 45)
    private String status;

    @Column(name = "maq_recipient", length = 255)
    private String recipient;

    @Column(name = "maq_subject", length = 255)
    private String subject;

    @Column(name = "maq_body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "maq_from", length = 255)
    private String from;

    @Column(name = "maq_recipient_name", length = 255)
    private String recipientName;

    @Column(name = "maq_from_name", length = 255)
    private String fromName;

    @Column(name = "maq_intentos", nullable = false)
    private Integer attempts = 0;

    @Column(name = "maq_attachfile", length = 100)
    private String attachFile;
}
