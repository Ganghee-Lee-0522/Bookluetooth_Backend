package cotato.Bookluetooth.comment.dto;

import cotato.Bookluetooth.review.Review;
import cotato.Bookluetooth.users.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentListFormat {
    private Long commentId;
    private Review reviewId;
    private String userName;
    private LocalDateTime commentDate;
    private String commentContent;
    private String userImage;

    @Builder
    public CommentListFormat(Long commentId, Review reviewId, String userName,
                             LocalDateTime commentDate, String commentContent, String userImage) {
        this.commentId = commentId;
        this.reviewId = reviewId;
        this.userName = userName;
        this.commentDate = commentDate;
        this.commentContent = commentContent;
        this.userImage = userImage;
    }
}
