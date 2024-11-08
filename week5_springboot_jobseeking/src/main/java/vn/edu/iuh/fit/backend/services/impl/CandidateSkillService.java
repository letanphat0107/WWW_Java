package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.CandidateSkill;
import vn.edu.iuh.fit.backend.repositories.ICandidateRepository;
import vn.edu.iuh.fit.backend.repositories.ICandidateSkillRepository;
import vn.edu.iuh.fit.backend.services.ICandidateSkillService;

@Service
public class CandidateSkillService implements ICandidateSkillService {
    @Autowired
    private ICandidateSkillRepository candidateSkillRepository;
    @Override
    public void save(CandidateSkill candidateSkill) {
        candidateSkillRepository.save(candidateSkill);
    }
}
