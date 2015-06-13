package org.springframework.examples.northwind.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


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

    @Column(name = "product_code", unique = false, nullable = false)
    private String productCode;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "category", unique = false, nullable = false)
    private String category;

    @Column(name="quantity_per_unit")
    private String quantityPerUnit;

    @Column(name="list_price")
    private BigDecimal listPrice;

    @Column(name="minimum_reorder_quantity")
    private Float minimumReorderQuantity;

    @Column(name="reorder_level")
    private Float reorderLevel;

    @Column(name="standard_cost")
    private BigDecimal standardCost;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<OrderDetails> orderDetails = new HashSet<OrderDetails>(0);


    public Product() {
    }
}
