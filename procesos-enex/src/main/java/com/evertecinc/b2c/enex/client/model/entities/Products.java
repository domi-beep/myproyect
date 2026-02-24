package com.evertecinc.b2c.enex.client.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", schema = "public")
public class Products {

    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "name")
    private String name;

    @Column(name = "product_status")
    private String productStatus;

}