package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private long order_id;
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> lstOrderDetail;

    @ManyToOne
    @Column(name = "cust_id")
    private Customer customer;

    @ManyToOne
    @Column(name = "employee_id")
    private Employee employee;

}
