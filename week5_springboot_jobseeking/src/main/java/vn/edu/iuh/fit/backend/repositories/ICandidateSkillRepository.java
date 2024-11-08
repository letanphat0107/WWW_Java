package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.CandidateSkill;
import vn.edu.iuh.fit.backend.models.CandidateSkillId;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;

@Repository
public interface ICandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
    @Query("SELECT cs.skill FROM CandidateSkill cs WHERE cs.id = ?1")
    public List<Skill> findSkillsByCandidateId(Long candidateId);
}
