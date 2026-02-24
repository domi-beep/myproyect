package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "storage_client_station", schema = "public")
@Getter
@Setter
public class StorageClientStation {

	@EmbeddedId
	private StorageClientStationId id;

	@Column(name = "litros", nullable = false, length = 45)
	private String litros;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", referencedColumnName = "id_client", insertable = false, updatable = false)
	private Clients client; 
	
}
