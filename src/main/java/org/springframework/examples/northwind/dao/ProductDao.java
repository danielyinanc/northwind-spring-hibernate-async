package org.springframework.examples.northwind.dao;

import org.springframework.examples.northwind.model.Product;

public interface ProductDao {

    void save(Product stock);
    void update(Product stock);
    void delete(Product stock);
    Product findByProductId(Integer productId);

}