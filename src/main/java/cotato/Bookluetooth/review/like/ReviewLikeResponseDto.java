package cotato.Bookluetooth.review.like;

import lombok.Getter;

@Getter
public class ReviewLikeResponseDto {
    private Long reviewId;
    private Long userId;

    public ReviewLikeResponseDto(ReviewLike reviewLike){
        this.reviewId = reviewLike.getReview().getReviewId();
        this.userId = reviewLike.getUsers().getUserId();
    }
}
