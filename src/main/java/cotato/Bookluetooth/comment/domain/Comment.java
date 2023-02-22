package cotato.Bookluetooth.comment.domain;

import cotato.Bookluetooth.BaseTimeEntity;
import cotato.Bookluetooth.review.Review;
import cotato.Bookluetooth.users.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users userId;

    @Column(nullable = false)
    private String commentContent;

    @Builder
    public Comment(Review reviewId, Users userId, String commentContent) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.commentContent = commentContent;
    }

    public Comment update(String commentContent) {
        this.commentContent = commentContent;

        return this;
    }
}