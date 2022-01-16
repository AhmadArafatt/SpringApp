package com.test.testpro.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.AjAttribute;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String body;
    //link
    @ManyToOne
    private Link link;
  }
