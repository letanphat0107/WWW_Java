package vn.edu.iuh.fit.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.entities.Candidate;

import javax.sql.DataSource;

@Component
public class CandidateRepository implements ICandidateRepository{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Candidate candidate) {
        String MariaDB = "INSERT INTO candidate (last_name, middle_name, first_name, dob, email, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(MariaDB, candidate.getLastName(), candidate.getMiddleName(), candidate.getFirstName(), candidate.getDob(), candidate.getEmail(), candidate.getAddress(), candidate.getPhone());
        System.out.println("Created Record Name = " + candidate.getLastName() + " " + candidate.getMiddleName() + " " + candidate.getFirstName());
        return;
    }

    @Override
    public Candidate update(Candidate candidate) {
        String MariaDB = "UPDATE candidate SET last_name = ?, middle_name = ?, first_name = ?, dob = ?, email = ?, address = ?, phone = ? WHERE id = ?";
        jdbcTemplate.update(MariaDB, candidate.getLastName(), candidate.getMiddleName(), candidate.getFirstName(), candidate.getDob(), candidate.getEmail(), candidate.getAddress(), candidate.getPhone(), candidate.getId());
        System.out.println("Updated Record with ID = " + candidate.getId());
        return candidate;
    }

    @Override
    public Candidate delete(Candidate candidate) {
        String MariaDB = "DELETE FROM candidate WHERE id = ?";
        jdbcTemplate.update(MariaDB, candidate.getId());
        System.out.println("Deleted Record with ID = " + candidate.getId());
        return candidate;
    }

    @Override
    public Candidate findById(int id) {
        String MariaDB = "SELECT * FROM candidate WHERE id = ?";
        Candidate candidate = jdbcTemplate.queryForObject(MariaDB, new Object[]{id}, (rs, rowNum) -> {
            Candidate c = new Candidate();
            c.setId(rs.getInt("id"));
            c.setLastName(rs.getString("last_name"));
            c.setMiddleName(rs.getString("middle_name"));
            c.setFirstName(rs.getString("first_name"));
            c.setDob(rs.getDate("dob").toLocalDate());
            c.setEmail(rs.getString("email"));
            c.setAddress(rs.getString("address"));
            c.setPhone(rs.getString("phone"));
            return c;
        });
        return candidate;
    }

    @Override
    public boolean setLevelForSkill(int candidateId, int skillId, int level) {
        String MariaDB = "INSERT INTO candidate_skill (candidate_id, skill_id, level) VALUES (?, ?, ?)";
        jdbcTemplate.update(MariaDB, candidateId, skillId, level);
        System.out.println("Set Level for Skill with ID = " + skillId + " for Candidate with ID = " + candidateId);
        return true;
    }
}
