package com.test.testpro.Controller;


import com.test.testpro.model.Comment;
import com.test.testpro.model.Link;
import com.test.testpro.repository.CommentRepository;
import com.test.testpro.repository.LinkRepository;
import com.test.testpro.service.CommentService;
import com.test.testpro.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
//@RequestMapping("LINKS")
public class LinkController {
    private LinkService linkService;
    private CommentService commentService;

    public LinkController(LinkService linkService, CommentService commentService) {
        this.linkService = linkService;
        this.commentService = commentService;
    }

    private static final Logger logger =LoggerFactory.getLogger(LinkController.class);


    @GetMapping("/")
    public String list1(Model model){
        model.addAttribute("links", linkService.findAll());
        return "link/list";
    }



    // list
    /*@GetMapping("/")
    public List<Link> list() {
        return linkRepository.findAll();
    }*/

    // CRUD
    @PostMapping("/create")
    public Link create(@ModelAttribute Link link) {
        return linkService.save(link);
    }


    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id,Model model) {
        Optional<Link> link = linkService.findById(id);// may or may not contain a non-null value
        if( link.isPresent() ) {
            Link currentLink= link.get();
            Comment comment=new Comment();
            comment.setLink(currentLink);
            model.addAttribute("link",currentLink);
            model.addAttribute("comment",comment);
            model.addAttribute("success", model.containsAttribute("success"));
            return "/link/view";
        } else {
            return "redirect:/list";///go to list.html
        }}


    @PutMapping("/{id}")
    public Link update(@ModelAttribute Link link) {
        return linkService.save(link);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        linkService.deleteById(id);
    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model) {
        model.addAttribute("link",new Link());
        return "link/submit";
    }

    @PostMapping("/link/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors())
        {
            logger.info("Validation error were found while submitting a new link");
            model.addAttribute("link",link);
            return "link/submit";
        }
        else{
            //save our link
            linkService.save(link);
            logger.info("New link wae saved successfully");
            redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success",true);
            // Flash attributes are an attributes that only live on the next template that you will visit
            return "redirect:/link/{id}";
        }

    }

    @Secured("ROLE_USER")
    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment , BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            logger.info("There was a problem adding new comment");
        }
        else{
            commentService.save(comment);
            logger.info("New comment was saved successfully");
        }

        return "redirect:/link/"+ comment.getLink().getId();
    }

}