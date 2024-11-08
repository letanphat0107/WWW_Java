package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.Experience;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Long> {
}
