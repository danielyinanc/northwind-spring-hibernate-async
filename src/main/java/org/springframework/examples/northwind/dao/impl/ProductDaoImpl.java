package org.springframework.examples.northwind.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Query;
import org.springframework.examples.northwind.dao.CustomHibernateDaoSupport;
import org.springframework.examples.northwind.dao.ProductDao;
import org.springframework.examples.northwind.model.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository("stockDao")
@Transactional
@Log4j2
public class ProductDaoImpl extends CustomHibernateDaoSupport implements ProductDao{

    public Boolean save(Product product){
        Serializable result = getHibernateTemplate().save(product);
        return (result!=null);
    }

    public Boolean update(Product product){
        getHibernateTemplate().update(product);
        return true;
    }

    public Boolean delete(Integer productId){
        String hql = "DELETE FROM Product p "  +
                "WHERE p.productId = :product_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("product_id", productId);
        int result = query.executeUpdate();
        return (result == 1);
    }

    @Override
    public Product findByProductId(Integer productId) {
        String hql = "from Product u where u.productId =:productId";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter ("productId", productId);
        List list = query.list();
        System.out.println("Result size:"+ list.size());
        if(list.size() > 0) {
            return (Product)list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Product> findAllProducts() {
        String hql = "from Product u";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);

        List<Product> list = (List<Product>) query.list();
        System.out.println("Result size:"+ list.size());
        if(list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }
}