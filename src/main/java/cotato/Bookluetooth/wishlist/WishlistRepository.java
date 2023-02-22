package cotato.Bookluetooth.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUsers_UserId(Long userId);
}
