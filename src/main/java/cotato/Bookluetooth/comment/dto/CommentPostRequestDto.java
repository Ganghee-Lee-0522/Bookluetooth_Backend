package cotato.Bookluetooth.comment.dto;

import cotato.Bookluetooth.comment.domain.Comment;
import cotato.Bookluetooth.review.Review;
import cotato.Bookluetooth.users.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentPostRequestDto {

    private Review reviewId;
    private String commentContent;

    @Builder
    public CommentPostRequestDto(Comment comment) {
        this.reviewId = comment.getReviewId();
        this.commentContent = comment.getCommentContent();
    }

    public Comment toEntity() {
        return Comment.builder()
                .reviewId(reviewId)
                .commentContent(commentContent)
                .build();
    }
}
