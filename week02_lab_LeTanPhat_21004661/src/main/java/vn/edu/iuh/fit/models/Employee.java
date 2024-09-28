package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import vn.edu.iuh.fit.enums.EmployeeStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employee")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e where e.status = :id")
})

public class Employee {
    @Id
    @Column(name = "emp_id")
    private long id;

    private LocalDateTime dob;

    private String email;
    private String address;
    private EmployeeStatus status;
    private String phone;
    @Column(name = "full_name")
    private String fullname;

    @OneToMany(mappedBy = "employee")
    private List<Order> lstOrder;

    public void setStatus(EmployeeStatus status){
        this.status = status;
    }
}
