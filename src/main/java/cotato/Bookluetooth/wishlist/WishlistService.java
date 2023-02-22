package cotato.Bookluetooth.wishlist;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.users.domain.UserRepository;
import cotato.Bookluetooth.users.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WishlistService{

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto save(WishlistRequestDto requestDto) throws Exception{
        try {
            if(requestDto == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                Long userId = requestDto.getUserId();
                Users user = userRepository.findById(userId)
                        .orElseThrow(()-> new IllegalArgumentException("유저 ID 오류, userId = " + userId));
                wishlistRepository.save(Wishlist.builder()
                        .users(user)
                        .bookIsbn(requestDto.getBookIsbn())
                        .bookTitle(requestDto.getBookTitle())
                        .bookImage(requestDto.getBookImage())
                        .bookAuthor(requestDto.getBookAuthor()).build());
                return new ResponseDto(200, "리뷰 수정 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<WishlistResponseDto> findByUserId(Long userId) throws Exception{
        try {
            return wishlistRepository.findByUsers_UserId(userId).stream()
                    .map(WishlistResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public ResponseDto delete(Long wishlistId) throws Exception {
        try {
            if(wishlistId == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                Wishlist wishlist = wishlistRepository.findById(wishlistId)
                        .orElseThrow(()-> new IllegalArgumentException("해당 위시리스트 서적이 존재하지 않습니다. wishlistId = " + wishlistId));
                wishlistRepository.delete(wishlist);
                return new ResponseDto(200, "댓글 좋아요 취소 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
