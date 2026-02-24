package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "station_clients", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StationClients {
	
    @EmbeddedId
    private StationClientsId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idClient")
	@JoinColumn(name = "id_client")
	private Clients client;
	
    @Column(name = "station_code", insertable = false, updatable = false)
    private String stationCode;

}