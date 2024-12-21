package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.Candidate;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ICandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT c.candidate FROM Experience c WHERE c.company = ?1")
    public List<Candidate> findByCompanyName(String companyName);

    //calc workExperience = to_date - from_date
    @Query("SELECT c.candidate FROM Experience c " +
            "GROUP BY c.candidate " +
            "HAVING SUM(YEAR(c.toDate) - YEAR(c.fromDate)) >= ?1")
    public List<Candidate> findByWorkExperienceGraterThan(int exp);

}
