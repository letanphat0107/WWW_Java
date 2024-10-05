import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import vn.edu.iuh.fit.backend.business.ProductBean;
import vn.edu.iuh.fit.backend.business.ProductBeanRemote;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductBeanTest {
    ProductBeanRemote productBean;
    @BeforeAll
    public void setUp() {
        productBean = new ProductBean();
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setImgPath("test.jpg");
        productBean.createProduct(product);
        assertNotNull(product.getId());
    }
}
