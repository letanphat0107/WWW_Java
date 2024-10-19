package vn.edu.iuh.fit.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.entities.Skill;

import javax.sql.DataSource;
import java.util.List;

@Component
public class SkillRepository implements ISkillRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Skill skill) {
        //Skill: name, description, field
        String MariaDB = "INSERT INTO skill (skill_name) VALUES (?)";
        jdbcTemplate.update(MariaDB, skill.getSkillName());
        System.out.println("Created Record Name = " + skill.getSkillName());
        return;
    }

    @Override
    public List<Candidate> getCandidatesBySkillAndLevel(String skillName, int level) {
        //Candidate ManyToMany Skill with level in CandidateSkill
        String MariaDB = "SELECT * FROM candidate WHERE id IN (SELECT candidate_id FROM candidate_skill WHERE skill_id IN (SELECT id FROM skill WHERE skill_name = ?) AND level >= ?)";

        List<Candidate> candidates = jdbcTemplate.query(MariaDB, new Object[]{skillName, level}, (rs, rowNum) -> {
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

        return candidates;
    }
}
