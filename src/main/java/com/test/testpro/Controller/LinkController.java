package com.test.testpro.Controller;


import com.test.testpro.model.Link;
import com.test.testpro.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private LinkRepository linkRepository;

    private static final Logger logger =LoggerFactory.getLogger(LinkController.class);
    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

        @GetMapping("/")
        public String list1(Model model){
        model.addAttribute("links",linkRepository.findAll());
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
        return linkRepository.save(link);
    }


    @GetMapping("/link/{id}")
   public String read(@PathVariable Long id,Model model) {
       Optional<Link> link = linkRepository.findById(id);// may or may not contain a non-null value
       if( link.isPresent() ) {
           model.addAttribute("link",link.get());
           model.addAttribute("success", model.containsAttribute("success"));
           return "/link/view";
       } else {
           return "redirect:/list";///go to list.html
       }}


    @PutMapping("/{id}")
    public Link update(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
     linkRepository.deleteById(id);
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
          linkRepository.save(link);
          logger.info("New link wae saved successfully");
          redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success",true);
          // Flash attributes are an attributes that only live on the next template that you will visit
          return "redirect:/link/{id}";
      }



    }

    }