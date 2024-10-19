package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "candidate")
@NoArgsConstructor
@ToString
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    public Candidate(String lastName, String middleName, String firstName, LocalDate dob, String email, String address, String phone) {
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}