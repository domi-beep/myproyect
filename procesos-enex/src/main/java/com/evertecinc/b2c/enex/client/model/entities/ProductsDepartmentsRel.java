package com.evertecinc.b2c.enex.client.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "products_departments_rel", schema = "public")
@IdClass(ProductsDepartmentsRel.ProductsDepartmentsRelId.class)
public class ProductsDepartmentsRel {

	@Id
	@Column(name = "id_department", nullable = false)
	private Long idDepartment;

	@Id
	@Column(name = "product_code", nullable = false, length = 10)
	private String productCode;

	@Id
	@Column(name = "document_type", nullable = false, length = 1)
	private String documentType;

	@Column(name = "remaining_amount", precision = 13, scale = 3)
	private BigDecimal remainingAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_department", referencedColumnName = "id_department", insertable = false, updatable = false)
	private Departments department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_code", referencedColumnName = "product_code", insertable = false, updatable = false)
	private Products product;

	@Getter
	@Setter
	@ToString
	@NoArgsConstructor
	public static class ProductsDepartmentsRelId implements Serializable {
		private static final long serialVersionUID = 7589234111149528533L;
		private Long idDepartment;
		private String productCode;
		private String documentType;

		public ProductsDepartmentsRelId(Long idDepartment, String productCode, String documentType) {
			this.idDepartment = idDepartment;
			this.productCode = productCode;
			this.documentType = documentType;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			ProductsDepartmentsRelId that = (ProductsDepartmentsRelId) o;

			if (!idDepartment.equals(that.idDepartment))
				return false;
			if (!productCode.equals(that.productCode))
				return false;
			return documentType.equals(that.documentType);
		}

		@Override
		public int hashCode() {
			int result = idDepartment.hashCode();
			result = 31 * result + productCode.hashCode();
			result = 31 * result + documentType.hashCode();
			return result;
		}
	}
}