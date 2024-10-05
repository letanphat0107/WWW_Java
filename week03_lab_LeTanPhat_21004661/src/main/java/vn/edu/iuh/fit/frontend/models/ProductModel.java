package vn.edu.iuh.fit.frontend.models;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

import java.util.List;

public class ProductModel {
    private final String ADD_URL = "/api/products";
    public void createProduct(Product product){
        try(Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(ADD_URL);
            // Gửi dữ liệu lên server
        }
    }

    public List<Product> getProducts(){
        return null;
    }
}
