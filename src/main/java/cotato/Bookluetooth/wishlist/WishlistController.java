package cotato.Bookluetooth.wishlist;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/post")
    public Long postWishlist(@RequestBody WishlistRequestDto requestDto){
        return wishlistService.save(requestDto);
    }

    @GetMapping("/{userId}")
    public List<WishlistResponseDto> getWishlistByUserId(@PathVariable Long userId){
        return wishlistService.findByUserId(userId);
    }

    @DeleteMapping("/delete/{wishlistId}")
    public Long deleteWishlist(@PathVariable Long wishlistId){
        wishlistService.delete(wishlistId);
        return wishlistId;
    }
}
