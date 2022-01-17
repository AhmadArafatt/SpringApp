package com.test.testpro.repository;

import com.test.testpro.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LinkRepository extends JpaRepository<Link,Long> {
    Link findByTitle(String title);
    List<Link> findAllByTitleLikeOrderByCreationDateDesc(String title);
}
