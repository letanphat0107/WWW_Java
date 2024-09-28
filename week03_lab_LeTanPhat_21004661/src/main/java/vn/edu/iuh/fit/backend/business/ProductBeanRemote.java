package vn.edu.iuh.fit.backend.business;

import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

public interface ProductBeanRemote {
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    Product getProduct(int id);
    List<Product> getProducts();
}
