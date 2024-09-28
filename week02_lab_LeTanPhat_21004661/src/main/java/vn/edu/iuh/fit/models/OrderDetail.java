package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private  double quantity;
    @Id
    private double price;

    private String note;

    @ManyToOne
    @Column(name = "product_id")
    private Product product;
    @ManyToOne
    @Column(name = "order_id")
    private Order order;
}
