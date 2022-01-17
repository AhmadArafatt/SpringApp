package com.test.testpro.repository;

import com.test.testpro.model.Comment;
import com.test.testpro.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
}
