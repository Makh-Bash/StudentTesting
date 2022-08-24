package ru.bashirov.studenttesting.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "main/index";
    }

    @GetMapping("/info")
    public String info() {
        return "main/info";
    }


}

@Controller
class IndexController implements ErrorController {
    @RequestMapping("/error")
    public String getErrorPath() {
        return "main/error";
    }
}
