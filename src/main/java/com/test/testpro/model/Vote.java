package com.test.testpro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Getter@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private int vote;


    //user
    //link

}
