package com.evertecinc.b2c.enex.client.model.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

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
@Table(name = "department_history", schema = "public")
@Data
@NoArgsConstructor
@DynamicInsert
@AllArgsConstructor
@Builder
public class DepartmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private Long idHistory;

    @Column(name = "id_department", nullable = false)
    private Long idDepartment;

    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "action", length = 255)
    private String action;

    @Column(name = "action_type", length = 1)
    private String actionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department", referencedColumnName = "id_department", insertable = false, updatable = false)
    private Departments department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private Users user;
}
