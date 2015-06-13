package org.springframework.examples.northwind.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_details", catalog = "northwind")
public class OrderDetails {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer orderDetailsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName="id", nullable = false)
    private Product product;


    public Product getProduct() {
        return this.product;
    }
}
