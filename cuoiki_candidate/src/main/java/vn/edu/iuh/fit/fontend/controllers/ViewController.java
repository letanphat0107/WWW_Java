package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.fit.backend.services.ICandidateService;

@Controller
public class ViewController {
    @Autowired
    private ICandidateService candidateService;
    @GetMapping("/login")
    public String login() {
        return "login"; // Trả về login.html
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("candidates", candidateService.findAll());
        return "main"; // Trả về main.html
    }
}
