package vn.edu.iuh.fit.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.services.ICandidateService;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateResource {
    @Autowired
    private ICandidateService candidateService;

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.findAll());
    }
    @RequestMapping("/company/{companyName}")
    public ResponseEntity<List<Candidate>> findByCompanyName(@PathVariable String companyName) {
        return ResponseEntity.ok(candidateService.findByCompanyName(companyName));
    }
}
