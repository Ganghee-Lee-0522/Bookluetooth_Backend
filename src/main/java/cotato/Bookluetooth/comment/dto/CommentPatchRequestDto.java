package cotato.Bookluetooth.comment.dto;

import cotato.Bookluetooth.comment.domain.Comment;
import cotato.Bookluetooth.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentPatchRequestDto {

    private Long commentId;
    private Review reviewId;
    private String commentContent;

    @Builder
    public CommentPatchRequestDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.reviewId = comment.getReviewId();
        this.commentContent = comment.getCommentContent();
    }
}
