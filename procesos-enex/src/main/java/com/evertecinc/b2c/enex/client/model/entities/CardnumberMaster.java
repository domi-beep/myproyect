package com.evertecinc.b2c.enex.client.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cardnumber_master", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CardnumberMaster {
	
	@Id
	@Column(name = "id_mifare")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idMifare;
	
	@Column(name = "xml")
	private String xml;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "date_use")
	private LocalDateTime dateUse;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vehicle")
	private Vehicles vehicle;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	

}
