package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

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
@Table(name = "orders", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@SequenceGenerator(
	    name = "ordersSeqGen",
	    sequenceName = "orders_id_order_seq",  // debe coincidir con tu secuencia en BD
	    allocationSize = 1            // INCREMENT BY 1 en la BD
	)
public class Orders {
	
	@Id
	@GeneratedValue(
	        strategy = GenerationType.SEQUENCE,
	        generator = "ordersSeqGen"
	    )
	@Column(name = "id_order", nullable = false)
	private Long idOrder;
	
	@Column(name = "id_client", nullable = false)
	private Long idClient;

	@Column(name = "id_user", nullable = false)
	private Long idUser;
	
	@Column(name = "total_order", nullable = false)
	private BigDecimal totalOrder;
	
	@Column(name = "pay_type",length=1, nullable = false)
	private String payType;
	
	@Column(name = "order_status",length=1, nullable = false)
	private String orderStatus;
	
	@Column(name = "paydate", nullable = true)
	private LocalDateTime payDate;
	
	@Column(name = "crtdate", nullable = false)
	private LocalDateTime crtDate;
	
	@Column(name = "order_type", nullable = false)
	private Long ordeType;
}
