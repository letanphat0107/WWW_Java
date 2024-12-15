package vn.edu.iuh.fit.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.services.ICandidateService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/candidates")
public class CandidateResource {
    @Autowired
    private ICandidateService candidateService;

    @RequestMapping("/all")
    public ResponseEntity<List<Candidate>> findAll() {
        List<Candidate> candidates = candidateService.findAllNoPaging();
        return ResponseEntity.ok(candidates);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Candidate> findById(@PathVariable Long id) {
        Candidate candidate = candidateService.getCandidate(id);
        return ResponseEntity.ok(candidate);
    }
}
