package com.evertecinc.b2c.enex.process.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "parameters", schema = "public")
@Data
public class Parameter {

    @Id
    @Column(name = "keyname", length = 45, nullable = false)
    private String keyName;

    @Column(name = "keyvalue", length = 45, nullable = false)
    private String keyValue;
}
