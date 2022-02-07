package com.test.testpro.Controller;

import com.test.testpro.model.User;
import com.test.testpro.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import javax.validation.Valid;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class AuthController {
    private final UserService userService;
    //private final Logger logger= (Logger) LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "auth/profile";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());

        return "auth/register";
    }


    @PostMapping("/register")
    public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() ) {
            //logger.info("Validation errors were found while registering a new user");
            model.addAttribute("user",user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "auth/register";
        } else {
            User newUser = userService.register(user);
            redirectAttributes
                    .addAttribute("id", newUser.getId())
                    .addFlashAttribute("success",true);
            return "redirect:/register";
             }
    }
    //BindingResult to check out that no validation errors happens
    //RedirectAttributes allows as to add some friendly messages and forward into next page
    // Flash attributes are an attributes that only live on the next template that you will visit

    @GetMapping("/activate/{email}/{activationCode}")
    public String activate(@PathVariable String email,@PathVariable String activationCode){
        Optional<User> user=userService.findByEmailAndActivationCode(email,activationCode);
        if(user.isPresent()){
        User newUser=user.get();
        newUser.setEnabled(true);//Now he can Log in
        newUser.setConfirmPassword(newUser.getPassword());//if you have a user obj, and you try to save
            // that user ; if the confirmation password is not there we will have a problem
        userService.save(newUser);
        userService.sendWelcomeEmail(newUser);
        return "auth/activated";

        }

        return "redirect:/";
    }


}
