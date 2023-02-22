package cotato.Bookluetooth.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewId(Long reviewId);
}
