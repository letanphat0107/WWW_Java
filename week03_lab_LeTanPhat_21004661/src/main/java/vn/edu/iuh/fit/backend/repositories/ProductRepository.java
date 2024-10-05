package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

public class ProductRepository {
    @PersistenceContext(unitName = "mariadb")
    private EntityManager entityManager;

    public void createProduct(Product product) {
        entityManager.persist(product);
    }

    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    public void deleteProduct(Product product) {
        entityManager.remove(entityManager.merge(product));
    }

    public Product getProduct(int id) {
        return entityManager.createNamedQuery("Product.findById", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Product> getProducts() {
        return entityManager.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }
}
