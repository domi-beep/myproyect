package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "departments_users_rel", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DepartmentsUsersRel {

    @EmbeddedId
    private DepartmentsUsersRelId id;

    @ManyToOne
    @MapsId("idDepartment")
    @JoinColumn(name = "departments_id_department")
    private Departments department;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "users_id_user")
    private Users user;
}

