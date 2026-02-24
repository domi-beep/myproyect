package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_card", schema = "public")
@IdClass(VehicleCardId.class)

public class VehicleCard {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_vehicle")
	private Vehicles vehicle;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_card")
	private Card card;
}
