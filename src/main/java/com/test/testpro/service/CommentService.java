package com.test.testpro.service;

import com.test.testpro.model.Comment;
import com.test.testpro.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final Logger logger= LoggerFactory.getLogger(CommentService.class);

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository =commentRepository;
    }
    public Comment save(Comment comment){

        return commentRepository.save(comment);
    }


}
