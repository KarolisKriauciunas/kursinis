package kursinis.main.model.domain.Forumas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long commentid;
    private Long replyid;
    private String content;
    @ManyToOne
    @JoinColumn(name = "forumid", nullable = false)
    private Forum forum;
}
