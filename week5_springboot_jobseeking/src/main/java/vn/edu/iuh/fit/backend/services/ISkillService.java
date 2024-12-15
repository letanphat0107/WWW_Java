package vn.edu.iuh.fit.backend.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;
import java.util.Optional;

@Service
public interface ISkillService {
    Skill addSkill(Skill skill);
    List<Skill> getAllSkills();
    List<Skill> suggestSkillsForCandidate(Long candidateId);
    Skill getSkillById(Long id);
}
