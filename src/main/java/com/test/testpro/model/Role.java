package com.test.testpro.model;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class Role {
    @Id@GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany( mappedBy = "roles")
    private Collection<User> users;

}