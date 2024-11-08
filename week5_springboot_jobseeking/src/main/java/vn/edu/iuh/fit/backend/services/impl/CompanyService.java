package vn.edu.iuh.fit.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.repositories.ICompanyRepository;
import vn.edu.iuh.fit.backend.services.ICompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private ICompanyRepository companyRepository;
    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company update(Company company) {
        return companyRepository.findById(company.getId()).map(selectCompany -> {
            selectCompany.setCompName(selectCompany.getCompName());
            selectCompany.setAddress(selectCompany.getAddress());
            selectCompany.setPhone(selectCompany.getPhone());
            selectCompany.setEmail(selectCompany.getEmail());
            return companyRepository.save(selectCompany);
        }).orElseThrow(() -> new IllegalArgumentException("Company not found"));
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Page<Company> getAllByPaging(int pageNo, int size, String sortField, String sortDir) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(pageNo, size, sort);
        return companyRepository.findAll(pageable);
    }
}
