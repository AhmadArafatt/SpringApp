package com.test.testpro;

import com.test.testpro.Config.SpringitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(SpringitProperties.class )
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "hello";
    }
}
