package org.springframework.examples.northwind.dao;

import org.springframework.examples.northwind.model.Product;

import java.util.List;
import java.util.concurrent.Future;

public interface ProductDao {
    Boolean save(Product stock);
    Boolean update(Product stock);
    Boolean delete(Integer productId);
    Product findByProductId(Integer productId);
    List<Product> findAllProducts();
}