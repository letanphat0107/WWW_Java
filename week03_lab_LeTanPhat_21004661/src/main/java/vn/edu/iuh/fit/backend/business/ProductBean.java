package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.backend.dtos.ProductDTO;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class ProductBean implements ProductBeanRemote{
    @Inject
    private ProductRepository productRepository;
    @Inject
    private ProductPriceRepository productPriceRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product);
    }

    @Override
    public ProductDTO getProduct(int id) {
        Product product = productRepository.getProduct(id);
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImgPath(),
                productPriceRepository.getActiveProductPrice(product.getId()).getValue());
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.getProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getImgPath(),
                    productPriceRepository.getActiveProductPrice(product.getId()).getValue()));
        }
        return null;
    }
}
