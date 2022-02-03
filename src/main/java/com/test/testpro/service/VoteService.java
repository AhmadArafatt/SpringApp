package com.test.testpro.service;

import com.test.testpro.model.Vote;
import com.test.testpro.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final Logger logger= LoggerFactory.getLogger(VoteService.class);

    public VoteService( VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }



    public Vote save(Vote vote){

        return voteRepository.save(vote);
    }


}
