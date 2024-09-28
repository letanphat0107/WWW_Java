package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_price")
public class ProductPrice {
    @Id
    private long price;

    @Id
    @ManyToOne
    @Column(name = "product_id")
    private Product product;
    private LocalDateTime price_date_time;
    private String note;
}
