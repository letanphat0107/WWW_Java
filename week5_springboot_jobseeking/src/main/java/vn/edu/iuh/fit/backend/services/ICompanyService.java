package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Company;

import java.util.List;
import java.util.Optional;

@Service
public interface ICompanyService {
    List<Company> getAll();

    Optional<Company> getById(Long id);

    void save(Company company);

    Company update(Company company);

    void delete(Long id);

    public Page<Company> getAllByPaging(int pageNo, int size, String sortField, String sortDir);
}
