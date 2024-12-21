package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import vn.edu.iuh.fit.backend.enums.Role;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "experience")
public class Experience implements Serializable {
    @Id
    @Column(name = "exp_id")
    private long id;

    private String company;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "work_des")
    private String description;

    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;

    public Experience() {
    }

    public Experience(long id, String company, LocalDate fromDate, LocalDate toDate, Role role, String description) {
        this.id = id;
        this.company = company;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.role = role;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", role=" + role +
                ", description='" + description + '\'' +
                ", candidate=" + candidate +
                '}';
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
