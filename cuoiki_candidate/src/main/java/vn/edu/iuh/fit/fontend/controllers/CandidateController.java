package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.services.ICandidateService;
import vn.edu.iuh.fit.fontend.models.CandidateModel;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private ICandidateService candidateService;
    @Autowired
    private CandidateModel candidateModel;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("candidates", candidateService.findAll());
        return "candidates";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,@PathVariable Long id) {
        model.addAttribute("candidate", candidateService.findById(id));
        return "cand_details";
    }

    @GetMapping("/goToReport1")
    public String goToReport1() {
        return "report1";
    }

    @GetMapping("/workIn")
    public String workIn(Model model,@RequestParam("companyName") String companyName) {
        model.addAttribute("candidates", candidateService.findByCompanyName(companyName));
        return "report1";
    }

    @GetMapping("/workExp/{exp}")
    public String workExp(Model model,@PathVariable int exp) {
        model.addAttribute("candidates", candidateService.findByWorkExperienceGraterThan(exp));
        return "report2";
    }

}
