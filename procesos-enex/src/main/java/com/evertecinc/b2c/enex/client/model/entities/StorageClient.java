package com.evertecinc.b2c.enex.client.model.entities;

import java.time.LocalDate;
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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "storage_cliente", schema = "public")
@Getter
@Setter
public class StorageClient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sto_cliente", nullable = false)
    private Long idStoCliente;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "notaria", length = 45)
    private String notaria;

    @Column(name = "notario", length = 45)
    private String notario;

    @Column(name = "dateins")
    private LocalDateTime dateIns;

    @Column(name = "dateupd")
    private LocalDateTime dateUpd;

    @Column(name = "usrins", length = 45)
    private String usrIns;

    @Column(name = "usrupd", length = 45)
    private String usrUpd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", insertable = false, updatable = false)
    private Clients client; 

}
