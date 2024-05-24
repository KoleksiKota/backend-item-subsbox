package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String createHomePage() {
        return "home";
    }
}
