package vn.edu.iuh.fit.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.entities.Job;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JobRepository implements IJobRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //Job: id, description
    @Override
    public void create(Job job) {
        String MariaDB = "INSERT INTO job (description) VALUES (?)";
        jdbcTemplate.update(MariaDB, job.getDescription());
        System.out.println("Created Record Description = " + job.getDescription());
    }

    @Override
    public List<Candidate> getRelevantCandidates(int jobId) {
        //Get skill_name, required_level from job_skill to use SkillRepository.getCandidatesBySkillAndLevel

        return List.of();
    }
}
