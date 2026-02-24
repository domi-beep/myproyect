package com.evertecinc.b2c.enex.email.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "email_config", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idemail")
    private Long idEmail;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "subject", length = 100)
    private String subject;

    @Column(name = "status", length = 1)
    private String status;
}
