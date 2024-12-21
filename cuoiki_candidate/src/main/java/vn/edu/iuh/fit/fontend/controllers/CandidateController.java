package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.models.Candidate;
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

    @GetMapping("/goToAdd")
    public String goToAddForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "add_candidate";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("candidate") Candidate candidate) {
        candidateService.save(candidate);
        return "redirect:/candidate/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        System.out.println("Delete id: " + id);
        candidateService.deleteById(id);
        return "redirect:/main";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("candidate", candidateService.findById(id));
        return "add_candidate";
    }
}
