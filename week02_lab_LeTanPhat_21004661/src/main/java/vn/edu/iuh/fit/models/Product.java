package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import vn.edu.iuh.fit.enums.ProductStatus;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    private long product_id;
    private String name;
    private String unit;
    private ProductStatus status;
    @Column(name = "manufacturer_name")
    private String manufacturer;
    private String description;

    @OneToMany
    private List<OrderDetail> orderDetails;
    @OneToMany
    private List<ProductImage> productImagesList;
    @OneToMany
    private List<ProductPrice> productPrices;
}
