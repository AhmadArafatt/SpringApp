package com.test.testpro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class Vote extends Auditable {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private short direction;

    @NonNull
    @ManyToOne// MANY VOTES BELONGS TO A SINGLE LINK
    private Link link;

    //user
    //link

}
