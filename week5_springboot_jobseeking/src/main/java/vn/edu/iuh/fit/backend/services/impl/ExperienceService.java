package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Experience;
import vn.edu.iuh.fit.backend.repositories.IExperienceRepository;
import vn.edu.iuh.fit.backend.services.IExperienceService;

@Service
public class ExperienceService implements IExperienceService {
    @Autowired
    private IExperienceRepository experienceRepository;
    @Override
    public void getAll() {
        experienceRepository.findAll();
    }

    @Override
    public void save(Experience experience) {
        experienceRepository.save(experience);
    }
}
