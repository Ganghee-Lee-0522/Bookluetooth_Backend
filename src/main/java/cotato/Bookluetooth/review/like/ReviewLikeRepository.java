package cotato.Bookluetooth.review.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike,ReviewLikeId> {
    List<ReviewLike> findByReview_ReviewId(Long reviewId);
}
