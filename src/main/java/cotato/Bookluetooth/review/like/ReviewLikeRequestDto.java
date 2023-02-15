package cotato.Bookluetooth.review.like;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewLikeRequestDto {
    private Long userId;
    private Long reviewId;
}
