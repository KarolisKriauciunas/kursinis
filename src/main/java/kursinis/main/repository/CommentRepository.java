package kursinis.main.repository;

import kursinis.main.model.domain.Forumas.Comment;
import kursinis.main.model.domain.Forumas.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByForum(Forum forumID);
}
