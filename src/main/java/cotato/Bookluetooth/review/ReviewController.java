package cotato.Bookluetooth.review;

import cotato.Bookluetooth.review.like.ReviewLikeRequestDto;
import cotato.Bookluetooth.review.like.ReviewLikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/post")
    public Long postReview(@RequestBody ReviewRequestDto requestDto){
        return reviewService.saveReview(requestDto);
    }

    @PostMapping("/like")
    public void likeReview(@RequestBody ReviewLikeRequestDto requestDto){
        reviewService.saveLike(requestDto);
    }

    @PatchMapping("/patch/{reviewId}")
    public Long patchReview(@PathVariable Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        return reviewService.update(reviewId, requestDto);
    }

    @GetMapping("/rid/{reviewId}")
    public ReviewResponseDto getReviewByReviewId(@PathVariable Long reviewId){
        return reviewService.findById(reviewId);
    }

    @GetMapping("/uid/{userId}")
    public List<ReviewResponseDto> getReviewByUserId(@PathVariable Long userId){
        return reviewService.findByUserId(userId);
    }

    @GetMapping("/like/{reviewId}")
    public List<ReviewLikeResponseDto> getLike(@PathVariable Long reviewId){
        return reviewService.findLike(reviewId);
    }

    @DeleteMapping("/delete/{reviewId}")
    public Long deleteReview(@PathVariable Long reviewId){
        reviewService.delete(reviewId);
        return reviewId;
    }

    @DeleteMapping("/dislike")
    public void dislikeReview(@RequestBody ReviewLikeRequestDto requestDto){
        reviewService.dislike(requestDto);
    }
}