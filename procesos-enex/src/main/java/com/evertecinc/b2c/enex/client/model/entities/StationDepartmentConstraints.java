package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "station_department_constraints", schema = "public")
@IdClass(StationDepartmentConstraintsId.class)
public class StationDepartmentConstraints {

    @Id
    @Column(name = "station_code", nullable = false, length = 10)
    private String stationCode;

    @Id
    @Column(name = "id_department", nullable = false)
    private Long idDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department", referencedColumnName = "id_department", insertable = false, updatable = false)
    private Departments department;
}


