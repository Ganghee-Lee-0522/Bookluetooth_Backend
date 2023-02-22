package cotato.Bookluetooth.review;

import cotato.Bookluetooth.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUsers_UserId(Long userId);
}
