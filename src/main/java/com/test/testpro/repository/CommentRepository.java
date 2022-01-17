package com.test.testpro.repository;

import com.test.testpro.model.Comment;
import com.test.testpro.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
