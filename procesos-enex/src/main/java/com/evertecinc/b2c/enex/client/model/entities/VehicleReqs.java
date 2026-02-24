package com.evertecinc.b2c.enex.client.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehicle_reqs", schema = "public")
@Data
public class VehicleReqs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_req", nullable = false)
	private Long idReq;

	@ManyToOne
	@JoinColumn(name = "id_client", nullable = false)
	private Clients client;

	@ManyToOne
	@JoinColumn(name = "id_department", nullable = false)
	private Departments department;

	@ManyToOne
	@JoinColumn(name = "vehicle_type_code", nullable = false)
	private VehicleTypes vehicleType;

	@ManyToOne
	@JoinColumn(name = "product_code", nullable = false)
	private Products product;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private Users user;

	@ManyToOne
	@JoinColumn(name = "id_user_aut")
	private Users userAut;

	@Column(name = "plate", length = 45)
	private String plate;

	@Column(name = "document_type", columnDefinition = "bpchar(1)")
	private String documentType;

	@Column(name = "dateins")
	private LocalDateTime dateIns;

	@Column(name = "dateout")
	private LocalDateTime dateOut;

	@Column(name = "status", columnDefinition = "bpchar(1)")
	private String status;

	@Column(name = "req_type", columnDefinition = "bpchar(1)")
	private String reqType;

	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "zone", length = 45)
	private String zone;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "phone", length = 20)
	private String phone;
}
