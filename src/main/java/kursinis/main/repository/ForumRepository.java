package kursinis.main.repository;

import kursinis.main.model.domain.Forumas.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum, Long> {
    List<Forum> findAllByForumID(Long id);
    Forum findForumByForumID(Long id);
}
