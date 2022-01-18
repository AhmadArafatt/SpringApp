package com.test.testpro.Controller;

import com.test.testpro.Config.SpringitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@EnableConfigurationProperties(SpringitProperties.class )
public class HomeController {
    @RequestMapping( path = "/", method = RequestMethod.GET)
    public String home(){
        return "index";
    }
}
