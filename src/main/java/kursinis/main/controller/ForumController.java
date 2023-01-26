package kursinis.main.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kursinis.main.model.api.Forum.ForumRequest;
import kursinis.main.model.api.Forum.ForumResponse;
import kursinis.main.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

public class ForumController {
    private final ForumService forumService;

    @Autowired
    ForumController(ForumService forumService)
    {
        this.forumService = forumService;
    }
    @PostMapping(path = "/create/forum")
    @Operation(summary = "Create forum instance in DB")
    @ApiResponse(
            responseCode = "201",
            description = "New Forum successfully created in DB",
            content = @Content(schema = @Schema(implementation = Long.class))
    )
    public Long createForum(@Validated @RequestBody ForumRequest request) {
        return forumService.createForum(request).getForumID();
    }


    @GetMapping(value = "/forums")
    @Operation(summary = "Get specific forum by ID or all if no ID provided")
    public List<ForumResponse> fetchForums(@RequestParam(required = false) Long forumID) {
        return forumService.fetchForums(forumID).stream()
                .map(p -> new ForumResponse(p.getForumID(),p.getForumName(),p.getPrivateAccess()))
                .collect(Collectors.toList());
    }
}
