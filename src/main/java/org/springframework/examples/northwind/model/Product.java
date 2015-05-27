package org.springframework.examples.northwind.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "products", catalog = "northwind")
public class Product {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer productId;

    @Column(name = "product_name", unique = false, nullable = false)
    private String productName;

    @Column(name="quantity_per_unit")
    private String quantityPerUnit;

    @Column(name="list_price")
    private BigDecimal listPrice;


    public Product() {
    }
}
