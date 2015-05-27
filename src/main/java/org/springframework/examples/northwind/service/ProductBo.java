package org.springframework.examples.northwind.service;

import org.springframework.examples.northwind.model.Product;

public interface ProductBo {

    void save(Product stock);
    void update(Product stock);
    void delete(Product stock);
    Product findByProductId(Integer productId);
}