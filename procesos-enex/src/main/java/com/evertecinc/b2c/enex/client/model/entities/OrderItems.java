package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
	    name = "orderItemsSeqGen",
	    sequenceName = "order_items_id_order_item_seq",  // coincida con el nombre real de la secuencia
	    allocationSize = 1                 // INCREMENT BY 1 en la BD
	)
public class OrderItems {
	
	@Id
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "orderItemsSeqGen"   // referencia al @SequenceGenerator de arriba
	    )
	@Column(name="id_order_item",nullable = false)
	private Long idOrderItem;
	
	@Column(name="id_order")
	private Long idOrder;
	
	@Column(name="id_client")
	private Long idClient;
	
	@Column(name="id_client_user")
	private Long idClientUser;
	
	@Column(name="id_card")
	private Long idCard;
	
	@Column(name="id_department")
	private Long idDepartment;
	
	@Column(name="id_vehicle")
	private Long idVehicle;
	
	@Column(name="product_code")
	private String productCode;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="document_type")
	private String documentType;
	
	@Column(name="id_documento")
	private String idDocumento;
	
	@Column(name="numero_documento")
	private String numeroDocumento;
	
	@Column(name="tipo_documento")
	private String tipoDocumento;
}
