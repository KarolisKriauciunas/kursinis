package kursinis.main.service;

import kursinis.main.model.api.Comment.CommentRequest;
import kursinis.main.model.domain.Forumas.Comment;
import kursinis.main.model.domain.Forumas.Forum;
import kursinis.main.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ForumService forumService;

    @Autowired
    public CommentService(CommentRepository commentRepository, ForumService forumService) {
        this.commentRepository = commentRepository;
        this.forumService = forumService;
    }

    public List<Comment> fetchComments(Long id) {
        List<Comment> comments = commentRepository.findAll().stream().filter(comment -> comment.getForum().getForumID().equals(id)).toList();
        return comments;
    }

    public Comment createComment(CommentRequest request) {
        Forum forum = forumService.fetchForum(request.getForumId());
        Comment comment = Comment.builder()
                .content(request.getContent())
                .forum(forum)
                .replyid(request.getReplyId())
                .build();

        return commentRepository.save(comment);
    }
}
