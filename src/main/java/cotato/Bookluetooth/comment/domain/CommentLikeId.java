package cotato.Bookluetooth.comment.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CommentLikeId implements Serializable {

    private Long comment;
    private Long review;
    private Long users;
}
