package cotato.Bookluetooth.review.like;

import cotato.Bookluetooth.review.BaseTimeEntity;
import cotato.Bookluetooth.review.Review;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ReviewLikeId.class)
public class ReviewLike extends BaseTimeEntity implements Serializable {

    @Id
    @ManyToOne(targetEntity = Review.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    @Id
    private Long userId;
}
