package vn.edu.iuh.fit.backend.repositories;

import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;

@Repository
public interface IJobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j WHERE j.company.id = :companyId")
    Page<List<Job>> findJobsByCompanyId_Paging(@Param("companyId") Long companyId, Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.company.id = ?1")
    public List<Job> findJobByCompanyId(Long companyId);
}
