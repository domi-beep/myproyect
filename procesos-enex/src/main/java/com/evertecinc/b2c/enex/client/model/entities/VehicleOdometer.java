package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_odometer", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleOdometer {
	
	@Id
	@Column(name = "plate", nullable = false)
	private String plate;
	
	@Column(name = "odometer", nullable = false)
	private BigDecimal odometer;

}
