package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Candidate;

import java.util.List;

public interface ICandidateRepository {
    public void create(Candidate candidate);
    public Candidate update(Candidate candidate);
    public Candidate delete(Candidate candidate);
    public Candidate findById(int id);

    public boolean setLevelForSkill(int candidateId, int skillId, int level);
}
