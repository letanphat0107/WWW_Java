package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.JobSkillId;

@Repository
public interface IJobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
}
