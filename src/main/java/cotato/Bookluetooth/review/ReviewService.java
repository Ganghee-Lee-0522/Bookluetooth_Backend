package cotato.Bookluetooth.review;

import cotato.Bookluetooth.review.like.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    @Transactional
    public Long saveReview(ReviewRequestDto requestDto) {
        return reviewRepository.save(requestDto.toEntity()).getReviewId();
    }

    @Transactional
    public void saveLike(ReviewLikeRequestDto requestDto) {
        Long targetId = requestDto.getReviewId();
        Review review = reviewRepository.findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + targetId));
        ReviewLike newReviewLike = new ReviewLike(review, requestDto.getUserId());
        reviewLikeRepository.save(newReviewLike);
    }

    @Transactional
    public Long update(Long reviewId, ReviewRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + reviewId));
        review.update(requestDto.getBookIsbn(), requestDto.getReviewContent(), requestDto.getBookPoint(),
                requestDto.getBookTitle() ,requestDto.getBookImage(), requestDto.getBookAuthor());
        return reviewId;
    }

    @Transactional
    public ReviewResponseDto findById(Long reviewId) {
        Review entity = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + reviewId));
        return new ReviewResponseDto(entity);
    }

    // TODO: 팔로잉하는 user들의 리뷰 모두 가져오기 -> 피드 구현을 위해 필요할듯
    @Transactional
    public List<ReviewResponseDto> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ReviewLikeResponseDto> findLike(Long reviewId) {
        return reviewLikeRepository.findByReview_ReviewId(reviewId).stream()
                .map(ReviewLikeResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + reviewId));
        reviewRepository.delete(review);
    }

    @Transactional
    public void dislike(ReviewLikeRequestDto requestDto) {
        ReviewLike like = reviewLikeRepository.findById(new ReviewLikeId(requestDto.getReviewId(),requestDto.getUserId()))
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + requestDto.getReviewId()));
        reviewLikeRepository.delete(like);
    }
}
