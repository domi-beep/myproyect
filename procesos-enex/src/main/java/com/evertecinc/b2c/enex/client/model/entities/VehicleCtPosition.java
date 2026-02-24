package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "vehicle_ct_position", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCtPosition {

    @Id
    @Column(name = "id_vehicle", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si es autoincremental
    private Long idVehicle;

    @Column(name = "data", length = 100)
    private String data;

}

