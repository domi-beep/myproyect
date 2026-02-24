package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "rut", length = 45)
    private String rut;

    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "role")
    private Integer role;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "num_retries", columnDefinition = "int default 0")
    private Integer numRetries;

    @Column(name = "date_last_login")
    private LocalDateTime dateLastLogin;

    @Column(name = "status", length = 1, columnDefinition = "bpchar default 'A'")
    private String status;

    @Column(name = "newpass", columnDefinition = "int default 1")
    private Integer newPass;
}