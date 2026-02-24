package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "area", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Long idArea;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "active", length = 1)
    private String active;

    // Relaciones con claves externas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region", referencedColumnName = "id_region", insertable = false, updatable = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city", referencedColumnName = "id_city", insertable = false, updatable = false)
    private City city;
}
