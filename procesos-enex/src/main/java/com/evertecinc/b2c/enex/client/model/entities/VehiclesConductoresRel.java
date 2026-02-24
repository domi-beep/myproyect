package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vehicles_conductores_rel", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class VehiclesConductoresRel {
	
	@EmbeddedId
    private VehiclesConductoresRelId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idConductor")
    @JoinColumn(name = "id_conductor")
    private Conductores conductor;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	    @MapsId("idVehicle")
	 @JoinColumn(name = "id_vehicle")
	 private Vehicles vehiculos;

}
