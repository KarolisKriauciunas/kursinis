package kursinis.main.model.api.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommentResponse {
    @Schema(description = "comment id", example = "1")
    private Long commentid;
    @Schema(description = "reply id",example =  "null")
    private Long replyid;
    @Schema(description = "content",example =  "hello tree world")
    private String content;
    @Schema(description = "forum id", example = "1")
    private Long forumid;
}
