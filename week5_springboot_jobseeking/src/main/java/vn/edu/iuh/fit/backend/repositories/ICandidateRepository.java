package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mapping.model.CamelCaseAbbreviatingFieldNamingStrategy;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;
import java.util.Set;

@Repository
@EnableJpaRepositories
public interface ICandidateRepository extends JpaRepository <Candidate, Long> {
    public Candidate findByEmail(String email);

    // Truy vấn ứng viên có kỹ năng phù hợp
    @Query("SELECT c FROM Candidate c JOIN c.candidateSkills cs WHERE cs.skill.id IN :skillIds")
    List<Candidate> findCandidatesBySkills(@Param("skillIds") Set<Long> skillIds);

    Candidate findCandidateByFullName(String username);
}
