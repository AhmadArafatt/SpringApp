package repository;

import com.test.testpro.model.Comment;
import com.test.testpro.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
