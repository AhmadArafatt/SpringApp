package com.test.testpro.service;

import com.test.testpro.model.Comment;
import com.test.testpro.model.Role;
import com.test.testpro.repository.CommentRepository;
import com.test.testpro.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final Logger logger= LoggerFactory.getLogger(RoleService.class);

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository =roleRepository;
    }
    public Role findByName( String name){

    return roleRepository.findByName(name);
    }


}
