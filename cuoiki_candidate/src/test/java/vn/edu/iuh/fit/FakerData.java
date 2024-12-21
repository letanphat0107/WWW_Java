package vn.edu.iuh.fit;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.repositories.ICandidateRepository;

@SpringBootTest
public class FakerData {
    @Autowired
    private ICandidateRepository candidateRepository;
    @Test
    public void generate() {
        Faker faker = new Faker();
        for (int i = 5; i < 10; i++) {
            Candidate candidate = new Candidate();
            candidate.setId((long) i);
            candidate.setFullname(faker.name().fullName());
            candidate.setEmail(faker.internet().emailAddress());
            candidate.setPhone(faker.phoneNumber().cellPhone());
            candidateRepository.save(candidate);
        }
    }
}
