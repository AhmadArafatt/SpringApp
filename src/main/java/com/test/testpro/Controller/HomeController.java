package com.test.testpro.Controller;

import com.test.testpro.Config.SpringitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableConfigurationProperties(SpringitProperties.class )
public class HomeController {
//
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("title","hello , thymleaf");
        return "home";
    }

}
