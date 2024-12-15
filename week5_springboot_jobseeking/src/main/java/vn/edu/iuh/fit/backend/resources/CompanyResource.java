package vn.edu.iuh.fit.backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.services.ICompanyService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/companies")
public class CompanyResource {
    @Autowired
    private ICompanyService companyService;

    @RequestMapping("/all")
    public ResponseEntity<List<Company>> findAll() {
        List<Company> companies = companyService.getAll();
        return ResponseEntity.ok(companies);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Company> getOne(@PathVariable Long id) {
        Company company = companyService.getById(id).get();
        return ResponseEntity.ok(company);
    }
}
