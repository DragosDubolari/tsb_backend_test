package com.tsb.lbl.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Spring web-app {@link Controller}. Its purpose in this Simple Blockchain Listener app is for demo.
 * Only home route is defined. The application can be extended in order to work with the events in a web app context.
 * */
@Controller
public class AppController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
}
