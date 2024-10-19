package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.entities.Job;

import java.util.List;

public interface IJobRepository {
    public void create(Job job);
    public List<Candidate> getRelevantCandidates(int jobId);
}
