package org.springframework.examples.northwind.service;

import org.springframework.examples.northwind.model.Product;
import org.springframework.util.concurrent.ListenableFuture;

public interface ProductBo {

    void save(Product stock);
    void update(Product stock);
    void delete(Product stock);
    ListenableFuture<Product> findByProductId(Integer productId);
}