package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

@Stateless
@LocalBean
public class ProductBean implements ProductBeanRemote{
    @PersistenceContext(unitName = "MariaDB")
    private EntityManager entityManager;

    @Override
    public void createProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void deleteProduct(Product product) {
        entityManager.remove(entityManager.merge(product));
    }

    @Override
    public Product getProduct(int id) {
        return entityManager.createNamedQuery("Product.findById", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Product> getProducts() {
        return entityManager.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }
}
