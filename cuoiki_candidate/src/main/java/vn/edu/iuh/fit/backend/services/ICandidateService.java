package vn.edu.iuh.fit.backend.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.enums.Role;
import vn.edu.iuh.fit.backend.models.Candidate;

import java.util.List;

@Service
public interface ICandidateService {
    public List<Candidate> findAll();
    public Candidate findById(Long id);
    public List<Candidate> findByCompanyName(String companyName);
    public List<Candidate> findByWorkExperienceGraterThan(int exp);

    public Boolean save(Candidate candidate);
    public void deleteById(Long id);
}
