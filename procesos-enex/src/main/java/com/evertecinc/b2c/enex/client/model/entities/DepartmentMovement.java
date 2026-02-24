package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department_movement")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov", nullable = false)
    private Long idMov;

    @Column(name = "id_department", nullable = false)
    private Long idDepartment;

    @Column(name = "date_in")
    private LocalDateTime dateIn;

    @Column(name = "movement", length = 100)
    private String movement;

    @Column(name = "documenttype", columnDefinition = "bpchar(1)")
    private String documentType;

    @Column(name = "monto", precision = 13, scale = 3)
    private BigDecimal monto;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "productname", length = 45)
    private String productName;

    @Column(name = "idref")
    private Long idRef;

    @Column(name = "type_balance", columnDefinition = "bpchar(1)")
    private String typeBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department", referencedColumnName = "id_department", insertable = false, updatable = false)
    private Departments department;
}
