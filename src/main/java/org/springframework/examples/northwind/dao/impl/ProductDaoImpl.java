package org.springframework.examples.northwind.dao.impl;

import org.hibernate.Query;
import org.springframework.examples.northwind.dao.CustomHibernateDaoSupport;
import org.springframework.examples.northwind.dao.ProductDao;
import org.springframework.examples.northwind.model.Product;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.Future;

@Repository("stockDao")
@Transactional
public class ProductDaoImpl extends CustomHibernateDaoSupport implements ProductDao{

    public void save(Product product){
        getHibernateTemplate().save(product);
    }

    public void update(Product product){
        getHibernateTemplate().update(product);
    }

    public void delete(Product product){
        getHibernateTemplate().delete(product);
    }

    @Override
    public Product findByProductId(Integer productId) {
        String hql = "from Product u where u.productId =:productId";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter ("productId", 1);
        List list = query.list();
        System.out.println("Result size:"+ list.size());
        if(list.size() > 0) {
            return (Product)list.get(0);
        } else {
            return null;
        }
    }
}