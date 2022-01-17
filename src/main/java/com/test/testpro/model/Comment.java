package com.test.testpro.model;

import lombok.*;
import org.aspectj.weaver.AjAttribute;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Comment extends Auditable {
    @Id
    @GeneratedValue

    private Long id;
    @NonNull
    private String body;
    //link
    @ManyToOne
    @NonNull
    private Link link;


}
