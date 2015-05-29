package org.springframework.examples.northwind.service.impl;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.examples.northwind.dao.ProductDao;
import org.springframework.examples.northwind.model.Product;
import org.springframework.examples.northwind.service.ProductBo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

@Service("productBo")
@Log4j2
public class ProductBoImpl implements ProductBo {

    @Autowired
    @Setter
    ProductDao productDao;

    @Override
    @Async
    public ListenableFuture<Boolean> save(Product product){
        return new AsyncResult<>(
                productDao.save(product)
        );
    }

    public ListenableFuture<Boolean> update(Product product){
        return new AsyncResult<>(
                productDao.update(product)
        );
    }

    @Override
    @Async
    public ListenableFuture<Boolean>  delete(Integer productId){
        return new AsyncResult<>(
                productDao.delete(productId)
        );

    }

    @Override
    @Async
    public ListenableFuture<Product> findByProductId(Integer productId) {
        return new AsyncResult<>(
                 productDao.findByProductId(productId)
        );
    }

    @Override
    @Async
    public ListenableFuture<List<Product>> findAllProducts() {
        return new AsyncResult<>(
                productDao.findAllProducts()
        );
    }


}