package com.test.testpro.service;

import com.test.testpro.model.User;
import com.test.testpro.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger= LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){

        return userRepository.save(user);
    }

    public User register(User  user){
        return user;
    }
    public void saveUsers(User...users){
        //begin transaction
        for (User user : users){
            logger.info("Saving User :"+ user.getEmail());
            userRepository.save(user);
        }

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
