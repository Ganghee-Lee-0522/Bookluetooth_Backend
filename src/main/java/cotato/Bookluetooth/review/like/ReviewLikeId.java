package cotato.Bookluetooth.review.like;

import cotato.Bookluetooth.review.Review;
import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReviewLikeId implements Serializable {
    private Long review;
    private Long users;
}
