package kursinis.main.controller;

import kursinis.main.model.api.Comment.CommentRequest;
import kursinis.main.model.api.Comment.CommentResponse;
import kursinis.main.service.CommentService;
import kursinis.main.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //create a get method that will return all comments with given forumID.
    @GetMapping(value = "/comments")
    public List<CommentResponse> fetchComments(@RequestParam(required = false) Long forumID) {
       return commentService.fetchComments(forumID).stream().map(p -> CommentResponse.builder()
               .content(p.getContent())
               .forumid(p.getForum().getForumID())
               .replyid(p.getReplyid())
               .commentid(p.getCommentid())
               .build()).toList();
        
    }
    //create a get method that will return all comments with given replyID.
    @PostMapping(value = "/create/comment")
    public void createComment(@RequestBody CommentRequest request) {
        commentService.createComment(request);
    }
}
