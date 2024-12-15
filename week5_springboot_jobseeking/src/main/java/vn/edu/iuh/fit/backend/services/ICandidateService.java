package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;
import java.util.Set;

@Service
public interface ICandidateService {
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);
    public List<Candidate> findAllNoPaging();
    public Candidate getByEmail(String email);

    public Candidate getCandidate(Long id);

    public List<Candidate> findCandidatesBySkills(Set<Long> skillIds);
    public List<Skill> getSkillsForCandidate(Long candidateId);
}
