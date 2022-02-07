package com.test.testpro.service;

import com.test.testpro.model.User;
import com.test.testpro.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final Logger logger= LoggerFactory.getLogger(UserService.class);
    private final BCryptPasswordEncoder encoder;
    private final MailService mailService;

    public UserService(UserRepository userRepository, RoleService roleService,MailService mailService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mailService=mailService;
        encoder = new BCryptPasswordEncoder();//You can make create a bean in a configuration class and use it to get autowired
        // instead of initials it here
    }

    public User save(User user){

        return userRepository.save(user);
    }

    public Optional<User> findByEmailAndActivationCode(String email,String activationCode){
    return userRepository.findByEmailAndActivationCode(email,activationCode);
    }

    public void saveUsers(User...users){
        //begin transaction
        for (User user : users){
            logger.info("Saving User :"+ user.getEmail());
            userRepository.save(user);
        }

    }


    public void sendActivationEmail(User user){
        mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user){
        mailService.sendWelcomeEmail(user);
    }


    public User register(User  user){
        //take the password from the form and encode it
        String sercet = "{bcrypt}"+encoder.encode(user.getPassword());
        user.setPassword(sercet);

        //CONFIRM PASSWORD
        user.setConfirmPassword(sercet);
        //If we don't set the confirmation password , the @PasswordMatch is going to
        // thrown a validation constraint error because password is encoded.

        //ASSIGN A ROLE TO THIS USER
        user.addRole(roleService.findByName("ROLE_USER"));
        //SET AN ACTIVATION CODE BY SENDING AN EMAIL
        user.setActivationCode(UUID.randomUUID().toString());
        //DISABLE THE USER

        //SAVE USER
        save(user);

        //SEND AN ACTIVATION EMil.
        sendActivationEmail(user);

        //return the user

        return user;
    }

//    public void saveUsers(User...users){
//        //begin transaction
//        for (User user : users){
//            logger.info("Saving User :"+ user.getEmail());
//            try {
//                userRepository.save(user);
//            } catch (Exception e) {
//  
//                //rollback Transaction
//            }
//
//        }
//        //Commit transaction
//    }
}
