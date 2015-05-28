package org.springframework.examples.northwind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.examples.northwind.dao.ProductDao;
import org.springframework.examples.northwind.dto.ProductDto;
import org.springframework.examples.northwind.model.Product;
import org.springframework.examples.northwind.service.ProductBo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

@Service("productBo")
public class ProductBoImpl implements ProductBo {

    @Autowired
    ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void save(Product product){
        productDao.save(product);
    }

    public void update(Product product){
        productDao.update(product);
    }

    public void delete(Product product){
        productDao.delete(product);
    }

    @Override
    @Async
    public ListenableFuture<Product> findByProductId(Integer productId) {
        return new AsyncResult<>(
                 productDao.findByProductId(productId)
        );
    }


}