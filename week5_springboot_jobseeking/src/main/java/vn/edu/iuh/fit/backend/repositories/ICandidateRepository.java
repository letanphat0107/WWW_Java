package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.Candidate;

@Repository
public interface ICandidateRepository extends JpaRepository <Candidate, Long> {
    public Candidate findByEmail(String email);
}
