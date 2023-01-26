package kursinis.main.model.api.Forum;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ForumResponse {
    private Long forumID;
    private String forumName;
    private Boolean Access;
}
