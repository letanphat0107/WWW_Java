package vn.edu.iuh.fit.backend.business;

import vn.edu.iuh.fit.backend.dtos.ProductDTO;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

public interface ProductBeanRemote {
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    ProductDTO getProduct(int id);
    List<ProductDTO> getProducts();
}
