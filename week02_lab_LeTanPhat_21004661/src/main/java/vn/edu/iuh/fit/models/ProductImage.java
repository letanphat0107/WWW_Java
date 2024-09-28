package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    private long image_id;
    private String path;
    private String alternative;

    @ManyToOne
    @Column(name = "product_id")
    private Product product;
}
