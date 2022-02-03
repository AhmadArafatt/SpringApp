package com.test.testpro.service;

import com.test.testpro.model.Link;
import com.test.testpro.model.User;
import com.test.testpro.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final Logger logger= LoggerFactory.getLogger(LinkService.class);

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Optional<Link> findById(long id){

        return linkRepository.findById(id);
    }
    public List<Link> findAll(){

        return linkRepository.findAll();
    }

    public Link save(Link link){

        return linkRepository.save(link);
    }
    public void deleteById(long id){

         linkRepository.deleteById(id);
    }

}
