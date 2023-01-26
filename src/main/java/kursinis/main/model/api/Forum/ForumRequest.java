package kursinis.main.model.api.Forum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForumRequest {
    @Schema(description = "forum", example = "pls raise pay")
    public String forumName;
    @Schema(description = "access", example = "true")
    public Boolean forumAccess;
}
