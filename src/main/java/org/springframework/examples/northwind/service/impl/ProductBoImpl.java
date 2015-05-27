package org.springframework.examples.northwind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.examples.northwind.dao.ProductDao;
import org.springframework.examples.northwind.model.Product;
import org.springframework.examples.northwind.service.ProductBo;
import org.springframework.stereotype.Service;

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
    public Product findByProductId(Integer productId) {
        return productDao.findByProductId(productId);
    }


}