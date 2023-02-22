package cotato.Bookluetooth.wishlist;

import cotato.Bookluetooth.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    // 위시리스트 추가
    @PostMapping("/post")
    public ResponseEntity<ResponseDto<Object>> postWishlist(@RequestBody WishlistRequestDto requestDto){
        try{
            ResponseDto responseDto = wishlistService.save(requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 위시리스트 조회
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<List<WishlistResponseDto>>> getWishlistByUserId(@PathVariable Long userId){
        try {
            List<WishlistResponseDto> wishlistResponseDtoList = wishlistService.findByUserId(userId);
            return ResponseEntity.ok().body(ResponseDto.response(200, "위시리스트 정보 가져오기 성공",
                    wishlistResponseDtoList));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 위시리스트 삭제
    @DeleteMapping("/delete/{wishlistId}")
    public ResponseEntity<ResponseDto<Object>> deleteWishlist(@PathVariable Long wishlistId){
        try {
            ResponseDto responseDto = wishlistService.delete(wishlistId);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.ok().body(ResponseDto.response(400, "위시리스트 삭제 실패"));
        }
    }
}
