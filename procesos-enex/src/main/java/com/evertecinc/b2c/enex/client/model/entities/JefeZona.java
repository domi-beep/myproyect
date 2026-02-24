package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "jefes_zona", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JefeZona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jefezona")
    private Long idJefeZona;

    @Column(name = "rut", length = 45)
    private String rut;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "status", length = 1)
    private String status;
}
