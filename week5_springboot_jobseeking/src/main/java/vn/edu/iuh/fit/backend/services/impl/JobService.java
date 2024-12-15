package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.IJobRepository;
import vn.edu.iuh.fit.backend.repositories.IJobSkillRepository;
import vn.edu.iuh.fit.backend.services.IJobService;

import java.util.List;

@Service
public class JobService implements IJobService {
    @Autowired
    private IJobRepository jobRepository;
    @Autowired
    private IJobSkillRepository jobSkillRepository;
    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Page<List<Job>> getJobsByCompanyI_Paging(Long companyId, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findJobsByCompanyId_Paging(companyId, pageable);
    }

    @Override
    public List<Job> getJobsByCompanyIda(Long companyId) {
        return jobRepository.findJobByCompanyId(companyId);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).get();
    }

    @Override
    public void save(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void saveJobSkill(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }

    @Override
    public List<Job> findJobsBySkills(List<Skill> skills) {
        return jobRepository.findJobsBySkills(skills);
    }

}
