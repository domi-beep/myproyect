package com.evertecinc.b2c.enex.client.model.entities;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    private Long idRegion;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "active", length = 1)
    private String active;

    // Relación con la entidad Zone
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zone", referencedColumnName = "id_zone", insertable = false, updatable = false)
    private Zone zone;
}
