package com.test.testpro.Controller;

import com.test.testpro.model.Link;
import com.test.testpro.model.Vote;
import com.test.testpro.repository.LinkRepository;
import com.test.testpro.repository.VoteRepository;
import com.test.testpro.service.LinkService;
import com.test.testpro.service.VoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
public class VoteController {

    private VoteService voteService;
    private LinkService linkService;

    public VoteController(VoteService voteService, LinkService linkService) {
        this.voteService = voteService;
        this.linkService = linkService;
    }
    //http://localhost:8080/vote/link/1/direction/-1/votecount/5
    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount) {
        Optional<Link> optionalLink = linkService.findById(linkID);
        if( optionalLink.isPresent() ) {
            Link link = optionalLink.get();
            Vote vote = new Vote(direction,link);
            voteService.save(vote);

            int updatedVoteCount = voteCount + direction;
            link.setVoteCount(updatedVoteCount);
            linkService.save(link);
            return updatedVoteCount;
        }

        return voteCount;
    }
}//  Not going to render a view out.
// Our view is going to call this controller and perform some action and return sth to view
