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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards_history", schema = "public")
public class CardsHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_history")
	private Long idHistory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_card", referencedColumnName = "id_card")
	private Cards card;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	private User user;

	@Column(name = "username", length = 45)
	private String username;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "action", length = 255)
	private String action;

	@Column(name = "action_type", columnDefinition = "bpchar(1)")
	private String actionType;
}