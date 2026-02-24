package com.evertecinc.b2c.enex.client.model.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DepartmentsUsersRelId implements Serializable {

	private static final long serialVersionUID = -2861364491578324869L;

	@Column(name = "departments_id_department")
    private Long idDepartment;

    @Column(name = "users_id_user")
    private Long idUser;
}
