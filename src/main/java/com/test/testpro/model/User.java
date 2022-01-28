package com.test.testpro.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;


    @NonNull
    @Size(min = 8, max = 20)
    @Column(nullable = false, unique = true)
    private String email;
    @NonNull
    @Column(length = 100)
    private String password;


    @NonNull
    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)//EAGER ===> inested of lazely loading the roles we just went to group them all
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    ) // join table ==> setting a new table for users and roles (third table gets created)
    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }//returning a list of authorities and spring security needs to know about this

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void addRole(Role userRole) {
        roles.add(userRole);
    }
    public void addRoles(Set<Role> roles){
        roles.forEach(this::addRole);
    }
}
