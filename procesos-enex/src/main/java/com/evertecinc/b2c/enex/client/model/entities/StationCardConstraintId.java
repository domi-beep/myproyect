package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StationCardConstraintId {

	@Column(name = "id_card")
	private Long idCard;

	@Column(name = "station_code")
	private String stationCode;
	

}