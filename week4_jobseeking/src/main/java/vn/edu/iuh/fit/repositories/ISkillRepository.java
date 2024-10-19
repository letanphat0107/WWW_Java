package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.entities.Skill;

import java.util.List;

public interface ISkillRepository {
    public void create(Skill skill);
    public List<Candidate> getCandidatesBySkillAndLevel(String skillName, int level);
}
