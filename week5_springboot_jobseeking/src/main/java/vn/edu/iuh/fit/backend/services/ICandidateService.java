package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Candidate;

import java.util.List;

@Service
public interface ICandidateService {
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);
    public List<Candidate> findAllNoPaging();
    public Candidate getByEmail(String email);

    public Candidate getCandidate(Long id);
}
