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
@Table(name = "clients_users_rel", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientsUsersRel {

    @EmbeddedId
    private ClientsUsersRelId id;

    @ManyToOne
    @MapsId("idClient")
    @JoinColumn(name = "id_client")
    private Clients client;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    private Users user;
}

