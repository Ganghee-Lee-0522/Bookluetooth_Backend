package cotato.Bookluetooth.review;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.config.auth.LoginUser;
import cotato.Bookluetooth.config.auth.SessionUser;
import cotato.Bookluetooth.review.like.ReviewLikeRequestDto;
import cotato.Bookluetooth.review.like.ReviewLikeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 등록
    @PostMapping("/post")
    public ResponseEntity<ResponseDto<Object>> postReview(@RequestBody ReviewRequestDto requestDto){
        try{
            ResponseDto responseDto = reviewService.saveReview(requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 리뷰 좋아요
    @PostMapping("/like")
    public ResponseEntity<ResponseDto<Object>> likeReview(@RequestBody ReviewLikeRequestDto requestDto){
        try{
            ResponseDto responseDto = reviewService.saveLike(requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 리뷰 수정
    @PatchMapping("/patch/{reviewId}")
    public ResponseEntity<ResponseDto<Object>> patchReview(@PathVariable Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        try {
            ResponseDto responseDto = reviewService.update(reviewId, requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 내 리뷰 조회
    @GetMapping("/myreview")
    public ResponseEntity<ResponseDto<List<ReviewResponseDto>>> getMyReview(@LoginUser SessionUser users){
        try {
            List<ReviewResponseDto> reviewResponseDto = reviewService.findMyReview(users);
            return ResponseEntity.ok().body(ResponseDto.response(200, "리뷰 정보 가져오기 성공",
                    reviewResponseDto));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 리뷰 ID로 리뷰 조회
    @GetMapping("/rid/{reviewId}")
    public ResponseEntity<ResponseDto<ReviewResponseDto>> getReviewByReviewId(@PathVariable Long reviewId){
        try {
            ReviewResponseDto reviewResponseDto = reviewService.findReviewByReviewId(reviewId);
            return ResponseEntity.ok().body(ResponseDto.response(200, "리뷰 정보 가져오기 성공",
                    reviewResponseDto));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 사용자 ID로 리뷰 조회
    @GetMapping("/uid/{userId}")
    public ResponseEntity<ResponseDto<List<ReviewResponseDto>>> getReviewByUserId(@PathVariable Long userId){
        try {
            List<ReviewResponseDto> reviewResponseDtoList = reviewService.findByUserId(userId);
            return ResponseEntity.ok().body(ResponseDto.response(200, "리뷰 정보 가져오기 성공",
                    reviewResponseDtoList));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 팔로어 리뷰 조회
    @GetMapping("/follower")
    public ResponseEntity<ResponseDto<List<ReviewResponseDto>>> getFollowerReview(@LoginUser SessionUser users){
        try {
            List<ReviewResponseDto> reviewResponseDtoList = reviewService.findFollowerReview(users);
            return ResponseEntity.ok().body(ResponseDto.response(200, "리뷰 정보 가져오기 성공",
                    reviewResponseDtoList));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 리뷰 좋아요 조회
    @GetMapping("/like/{reviewId}")
    public ResponseEntity<ResponseDto<List<ReviewLikeResponseDto>>> getLike(@PathVariable Long reviewId){
        try {
            List<ReviewLikeResponseDto> likeResponseDtoList = reviewService.findLike(reviewId);
            return ResponseEntity.ok().body(ResponseDto.response(200, "리뷰 좋아요 정보 가져오기 성공",
                    likeResponseDtoList));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, "존재하지 않는 리뷰 아이디"));
        }
    }

    // 리뷰 삭제
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<ResponseDto<Object>> deleteReview(@PathVariable Long reviewId){
        try {
            ResponseDto responseDto = reviewService.delete(reviewId);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.ok().body(ResponseDto.response(400, "리뷰 삭제 실패"));
        }
    }

    // 리뷰 좋아요 취소
    @DeleteMapping("/dislike")
    public ResponseEntity<ResponseDto<Object>> dislikeReview(@RequestBody ReviewLikeRequestDto requestDto){
        try {
            ResponseDto responseDto = reviewService.dislike(requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, "해당 리뷰가 존재하지 않습니다"));
        }
    }
}