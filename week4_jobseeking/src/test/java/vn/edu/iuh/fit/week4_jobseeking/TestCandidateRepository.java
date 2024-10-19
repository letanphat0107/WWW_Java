package vn.edu.iuh.fit.week4_jobseeking;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.repositories.CandidateRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCandidateRepository {
    private CandidateRepository candidateRepository;

    @BeforeAll
    public void init() {
        candidateRepository = new CandidateRepository();
    }

    @Test
    public void testCreate() {
        Candidate candidate = new Candidate("Nguyen", "Van", "B", java.time.LocalDate.now(), "mail", "address", "phone");
        candidateRepository.create(candidate);
    }
}
