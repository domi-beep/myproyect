package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class StorageClientStationId {
	
	@Column(name = "id_client", nullable = false)
    private Long idClient;

    @Column(name = "station_code", nullable = false, length = 10)
    private String stationCode;

}
