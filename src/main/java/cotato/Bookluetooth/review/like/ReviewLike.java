package cotato.Bookluetooth.review.like;

import cotato.Bookluetooth.BaseTimeEntity;
import cotato.Bookluetooth.review.Review;
import cotato.Bookluetooth.users.domain.Users;
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
    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;
}
