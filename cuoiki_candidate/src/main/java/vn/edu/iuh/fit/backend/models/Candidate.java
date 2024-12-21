package vn.edu.iuh.fit.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "candidate")
@NamedQueries({
        @NamedQuery(name = "Candidate.findAll", query = "select c from Candidate c"),
        @NamedQuery(name = "Candidate.findDevByCompany", query = "select c from Candidate c inner join c.experiences experiences where experiences.company like concat('%', :companyName, '%') ")
})
public class Candidate implements Serializable {

    @Id
    @Column(name = "can_id")
    private long id;
    @Column(name = "full_name")
    private String fullname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "candidate")
    @JsonIgnore
    private List<Experience> experiences;

    public Candidate() {
    }

    public Candidate(long id, String fullname, String phone, String email) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }
}
