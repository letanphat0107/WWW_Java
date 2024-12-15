package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.ICandidateRepository;
import vn.edu.iuh.fit.backend.repositories.ISkillRepository;
import vn.edu.iuh.fit.backend.services.ICandidateService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidateService implements ICandidateService {
    @Autowired
    private ICandidateRepository candidateRepository;
    @Autowired
    private ISkillRepository skillRepository;

    @Override
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);
    }

    @Override
    public List<Candidate> findAllNoPaging() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    @Override
    public Candidate getCandidate(Long id) {
        return candidateRepository.findById(id).get();
    }

    @Override
    public List<Candidate> findCandidatesBySkills(Set<Long> skillIds) {
        return candidateRepository.findCandidatesBySkills(skillIds);
    }

    @Override
    public List<Skill> getSkillsForCandidate(Long candidateId) {
        // Lấy ứng viên từ cơ sở dữ liệu
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));

        // Lấy kỹ năng của ứng viên
        return candidate.getCandidateSkills().stream()
                .map(candidateSkill -> candidateSkill.getSkill())
                .collect(Collectors.toList());
    }

}
