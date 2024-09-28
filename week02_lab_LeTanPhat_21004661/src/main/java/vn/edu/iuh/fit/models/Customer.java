package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "cust_id")
    private long id;

    private String email;
    private String address;
    private String phone;
    @Column(name = "cust_name")
    private String name;

    @OneToMany(mappedBy = "customer")
    List<Order> lstOrder;
}
