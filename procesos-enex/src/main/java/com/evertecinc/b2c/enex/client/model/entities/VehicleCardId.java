package com.evertecinc.b2c.enex.client.model.entities;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCardId implements Serializable {

	private static final long serialVersionUID = -8149226393868661644L;
	private Vehicles vehicle;
	private Card card;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		VehicleCardId that = (VehicleCardId) o;
		return Objects.equals(vehicle, that.vehicle) && Objects.equals(card, that.card);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vehicle, card);
	}

}