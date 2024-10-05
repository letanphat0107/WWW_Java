package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.repositories.entities.ProductPrice;

import java.util.List;

public class ProductPriceRepository {
    @PersistenceContext(unitName = "mariadb")
    private EntityManager entityManager;

    public void createProductPrice(ProductPrice productPrice) {
        entityManager.persist(productPrice);
    }

    public void updateProductPrice(ProductPrice productPrice) {
        entityManager.merge(productPrice);
    }

    public void deleteProductPrice(ProductPrice productPrice) {
        entityManager.remove(entityManager.merge(productPrice));
    }

    public ProductPrice getProductPrice(int id) {
        return entityManager.createNamedQuery("ProductPrice.findById", ProductPrice.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<ProductPrice> getProductPrices() {
        return entityManager.createNamedQuery("ProductPrice.findAll", ProductPrice.class)
                .getResultList();
    }

    public ProductPrice getActiveProductPrice(int id) {
        return entityManager.createNamedQuery("findAActivePriceByProduct", ProductPrice.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
