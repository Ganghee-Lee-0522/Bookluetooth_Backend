package cotato.Bookluetooth.comment.domain;

import cotato.Bookluetooth.review.Review;
import cotato.Bookluetooth.users.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@IdClass(CommentLikeId.class)
public class CommentLike implements Serializable {

    @Id
    @ManyToOne(targetEntity = Comment.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "commentId")
    private Comment comment;

    @Id
    @ManyToOne(targetEntity = Review.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    @Id
    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;
}
