package kursinis.main.model.api.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequest {
    @Schema(description = "content",example =  "hello tree world")
    private String content;
    @Schema(description = "forum id", example = "1")
    private Long forumId;
    @Schema(description = "reply id", example = "null")
    private Long replyId;
}
