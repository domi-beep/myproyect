package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_types", schema = "public")
public class VehicleTypes {

	@Id
	@Column(name = "vehicle_type_code")
	private String vehicleTypeCode;

	@Column(name = "name")
	private String name;

	@Column(name = "active")
	private String active;

}