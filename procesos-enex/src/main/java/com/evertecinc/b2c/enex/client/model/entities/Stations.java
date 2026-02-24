package com.evertecinc.b2c.enex.client.model.entities;

import com.evertecinc.b2c.enex.client.model.converters.BooleanToSmallintConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "stations")
@Getter
@Setter
@ToString
@IdClass(StationsId.class)
public class Stations {

    @Id
    @Column(name = "station_code", nullable = false)
    private String stationCode;
    
    @Id
    @Column(name = "client_type", nullable = false)
    private String clientType;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;

    @Column(name = "ct")
    @Convert(converter = BooleanToSmallintConverter.class)
    private Boolean ct;

    @Column(name = "lat")
    private String lat;

    @Column(name = "longitud")
    private String longitud;

    @Column(name = "code_electrolinera")
    private String codeElectrolinera;
}