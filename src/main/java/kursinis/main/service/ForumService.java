package kursinis.main.service;


import kursinis.main.model.api.Forum.ForumRequest;
import kursinis.main.model.domain.Forumas.Forum;
import kursinis.main.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

private ForumRepository forumRepository;
    @Autowired
    public ForumService(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    public List<Forum> fetchForums(Long id) {
        if (id != null) return forumRepository.findAllByForumID(id);
        return forumRepository.findAll();
    }
    public Forum fetchForum(Long id) {
        return forumRepository.findForumByForumID(id);
    }
    public Forum createForum(ForumRequest request) {
        Forum forum = Forum.builder()
                .forumName(request.getForumName())
                .privateAccess(request.getForumAccess())
                .build();

        return forumRepository.save(forum);
    }

}
