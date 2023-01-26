package kursinis.main.model.domain.Forumas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "forums")
@NoArgsConstructor
@AllArgsConstructor
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long forumID;
    private String forumName;
    private Boolean privateAccess;
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

}
