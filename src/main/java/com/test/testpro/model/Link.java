package com.test.testpro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class Link extends Auditable {
    @Id
    @GeneratedValue
    private long id;
    @NonNull
    private String title;
    @NonNull
    private String url;

//comment
    @OneToMany(mappedBy = "link")//one link mapped to many comments///mappedBy ==> using it i can say : give the link that this comment belongs to
    private List<Comment> comments=new ArrayList<>();


    public void addComment(Comment comment)
    {
        comments.add(comment);
    }
}
