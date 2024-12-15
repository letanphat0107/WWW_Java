package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;

import java.util.List;

@Service
public interface IJobService {
    public List<Job> getAllJobs();
    public Page<List<Job>> getJobsByCompanyI_Paging(Long companyId, int pageNo, int pageSize, String sortBy, String sortDirection);
    public List<Job> getJobsByCompanyIda(Long companyId);
    public Job getJobById(Long id);
    public void save(Job job);
    public void saveJobSkill(JobSkill jobSkill);

    public List<Job> findJobsBySkills(List<Skill> skills);
}
