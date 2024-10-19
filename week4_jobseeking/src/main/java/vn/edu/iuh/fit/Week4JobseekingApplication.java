package vn.edu.iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.repositories.CandidateRepository;
import vn.edu.iuh.fit.repositories.JobRepository;
import vn.edu.iuh.fit.repositories.SkillRepository;

import javax.sql.DataSource;

@SpringBootApplication
public class Week4JobseekingApplication {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobRepository jobRepository;
    public static void main(String[] args) {
        SpringApplication.run(Week4JobseekingApplication.class, args);
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public CommandLineRunner test() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Candidate candidate = new Candidate("Nguyen", "Van", "A", java.time.LocalDate.now(), "mail", "address", "phone");
//                candidateRepository.create(candidate);

                System.out.println(candidateRepository.findById(1));
//                skillRepository.create(new Skill("Java", "Java", "IT"));
//                candidateRepository.setLevelForSkill(1, 1, 5);
                System.out.println(skillRepository.getCandidatesBySkillAndLevel("Java", 3));
            }
        };
    }
}
