package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zone", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zone")
    private Long idZone;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "active", length = 1)
    @Builder.Default
    private String active = "A"; // Valor por defecto 'A'
}

