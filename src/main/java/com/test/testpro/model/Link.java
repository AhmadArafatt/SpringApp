package com.test.testpro.model;


import com.test.testpro.service.BeanUtil;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor

@NoArgsConstructor
public class Link extends Auditable {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    @NotEmpty(message = "Please enter a title.")
    private String title;
    @NonNull
    @NotEmpty(message = "Please enter a url.")
    @URL(message = "Please enter a valid url.")
    private String url;




//comment
    @OneToMany(mappedBy = "link")//one link mapped to many comments///mappedBy ==> using it i can say : give the link that this comment belongs to
    private List<Comment> comments=new ArrayList<>();

    @OneToMany(mappedBy = "link")
    private List<Vote> votes=new ArrayList<>();

    private int voteCount=0;

    public void addComment(Comment comment)
    {
        comments.add(comment);
    }

    public String getDomainName() throws URISyntaxException {
        URI uri = new URI(this.url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public String getPrettyTime() {
       // PrettyTime pt = new PrettyTime(); // we only need one instance of it
         PrettyTime pt = BeanUtil.getBean(PrettyTime.class); // we only need one instance of it

        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    } // pretty need a date time obj not a local date time


}
