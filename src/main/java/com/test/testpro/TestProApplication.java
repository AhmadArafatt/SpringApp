package com.test.testpro;

import com.test.testpro.model.Comment;
import com.test.testpro.model.Link;
import com.test.testpro.repository.CommentRepository;
import com.test.testpro.repository.LinkRepository;
import lombok.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestProApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestProApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
        return args->{
        //Create a link and save it to database
        Link link=new Link("Getting statred with spring boot 2","https://www.danvega.dev/docs/spring-boot-2-docs/#_course_details");
        linkRepository.save(link);

        //Create a Comment and save it to database
        Comment comment = new Comment("This is a nice link",link);
        commentRepository.save(comment);

        //attach link to its comment
        link.addComment(comment);
        Link firstLink=linkRepository.findByTitle("Getting statred with spring boot 2");
        System.out.println(firstLink.getTitle());

        };
    }


}
