package vn.edu.iuh.fit.fontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/login")
    public String login() {
        return "login"; // Trả về login.html
    }

    @GetMapping("/main")
    public String main() {
        return "main"; // Trả về main.html
    }
}
