package com.evertecinc.b2c.enex.client.model.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class VehiclesConductoresRelId implements Serializable {

	private static final long serialVersionUID = -2861364491578324869L;

	@Column(name = "id_conductor")
    private Long idConductor;

    @Column(name = "id_vehicle")
    private Long idVehicle;
}
