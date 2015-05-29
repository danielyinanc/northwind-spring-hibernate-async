package org.springframework.examples.northwind.service;

import org.springframework.examples.northwind.model.Product;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

public interface ProductBo {
    ListenableFuture<Boolean>  save(Product product);
    ListenableFuture<Boolean>  update(Product product);
    ListenableFuture<Boolean> delete(Integer productId);
    ListenableFuture<Product> findByProductId(Integer productId);
    ListenableFuture<List<Product>> findAllProducts();
}