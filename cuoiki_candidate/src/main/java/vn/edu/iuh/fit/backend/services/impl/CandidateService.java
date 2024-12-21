package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.enums.Role;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.repositories.ICandidateRepository;
import vn.edu.iuh.fit.backend.services.ICandidateService;

import java.util.List;

@Service
public class CandidateService implements ICandidateService {
    @Autowired
    private ICandidateRepository candidateRepository;

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    @Override
    public List<Candidate> findByCompanyName(String companyName) {
        return candidateRepository.findByCompanyName(companyName);
    }

    @Override
    public List<Candidate> findByWorkExperienceGraterThan(int exp) {
        return candidateRepository.findByWorkExperienceGraterThan(exp);
    }
}
