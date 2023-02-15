package cotato.Bookluetooth.wishlist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WishlistService{

    private final WishlistRepository wishlistRepository;

    @Transactional
    public Long save(WishlistRequestDto requestDto) {
        return wishlistRepository.save(requestDto.toEntity()).getWishlistId();
    }

    @Transactional(readOnly = true)
    public List<WishlistResponseDto> findByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId).stream()
                .map(WishlistResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(()-> new IllegalArgumentException("해당 위시리스트 서적이 존재하지 않습니다. wishlistId = " + wishlistId));
        wishlistRepository.delete(wishlist);
    }
}
