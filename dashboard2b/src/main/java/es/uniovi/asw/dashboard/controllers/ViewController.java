package es.uniovi.asw.dashboard.controllers;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    private static final Logger logger = Logger.getLogger(ViewController.class);

    @RequestMapping("/")
    public String landing(Model model) {
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    @RequestMapping("/navbar")
    public String navbar(Model model) {
        return "navbar";
    }

    @RequestMapping("/alcalde")
    public String alcalde(Model model) {
        return "alcalde";
    }

    @RequestMapping("/concejal")
    public String concejal(Model model) {
        return "concejal";
    }
}