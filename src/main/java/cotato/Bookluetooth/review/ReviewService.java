package cotato.Bookluetooth.review;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.config.auth.SessionUser;
import cotato.Bookluetooth.follow.Follow;
import cotato.Bookluetooth.follow.FollowRepository;
import cotato.Bookluetooth.review.like.*;
import cotato.Bookluetooth.users.domain.UserRepository;
import cotato.Bookluetooth.users.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public ResponseDto saveReview(SessionUser user, ReviewRequestDto requestDto) throws Exception{
        try {
            if(user == null || requestDto == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                Users writer = userRepository.findByUserEmail(user.getUserEmail())
                        .orElseThrow(()-> new IllegalArgumentException("유저 ID 오류"));
                reviewRepository.save(Review.builder()
                        .users(writer)
                        .bookIsbn(requestDto.getBookIsbn())
                        .reviewContent(requestDto.getReviewContent())
                        .bookPoint(requestDto.getBookPoint())
                        .bookTitle(requestDto.getBookTitle())
                        .bookImage(requestDto.getBookImage())
                        .bookAuthor(requestDto.getBookAuthor())
                        .build());
                return new ResponseDto(200, "리뷰 등록 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public ResponseDto saveLike(ReviewLikeRequestDto requestDto) throws Exception{
        try {
            if(requestDto == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                Long reviewId = requestDto.getReviewId();
                Review review = reviewRepository.findById(reviewId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + reviewId));
                Long userId = requestDto.getUserId();
                Users user = userRepository.findById(userId)
                        .orElseThrow(()-> new IllegalArgumentException("유저 ID 오류, userId = " + userId));
                ReviewLike newReviewLike = new ReviewLike(review, user);
                reviewLikeRepository.save(newReviewLike);
                return new ResponseDto(200, "리뷰 좋아요 성공");
            }
        }
        catch (Exception e) {
                throw new Exception(e.getMessage());
            }
    }

    @Transactional
    public ResponseDto update(Long reviewId, ReviewRequestDto requestDto) throws Exception{
        try {
            if(requestDto == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                Review review = reviewRepository.findById(reviewId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + reviewId));
                review.update(requestDto.getBookIsbn(), requestDto.getReviewContent(), requestDto.getBookPoint(),
                        requestDto.getBookTitle() ,requestDto.getBookImage(), requestDto.getBookAuthor());
                return new ResponseDto(200, "리뷰 수정 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public ReviewResponseDto findReviewByReviewId(Long reviewId) throws Exception {
        try {
            Review entity = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + reviewId));
            return new ReviewResponseDto(entity);
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> findMyReview(SessionUser users) throws Exception {
        try {
            Long userId = userRepository.findByUserEmail(users.getUserEmail())
                    .orElseThrow(() -> new IllegalArgumentException
                            ("존재하지 않는 사용자 userEmail = " + users.getUserEmail())).getUserId();
            return reviewRepository.findByUsers_UserId(userId).stream()
                    .map(ReviewResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // TODO: 팔로잉하는 user들의 리뷰 모두 가져오기 -> 피드 구현을 위해 필요할듯
    public List<ReviewResponseDto> findFollowerReview(SessionUser users) throws Exception{
        try {
            List<Review> reviewList = new ArrayList<>();

            Users user = userRepository.findByUserEmail(users.getUserEmail()).get();

            List<Follow> followList = followRepository.findByFolloweeUser_UserId(user.getUserId())
                    .stream().collect(Collectors.toList());

            List<Long> userIdList = followList.stream().map(got -> got.getFollowerUser()
                    .getUserId()).collect(Collectors.toList());

            ListIterator<Long> longListIterator = userIdList.listIterator();
            while(longListIterator.hasNext()){
                List<Review> reviews = reviewRepository.findByUsers_UserId(longListIterator.next());
                ListIterator<Review> reviewListIterator = reviews.listIterator();
                while (reviewListIterator.hasNext()){
                    reviewList.add(reviewListIterator.next());
                }
            }

            return reviewList.stream().map(ReviewResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<ReviewResponseDto> findByUserId(Long userId) throws Exception{
        try {
            return reviewRepository.findByUsers_UserId(userId).stream()
                    .map(ReviewResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<ReviewLikeResponseDto> findLike(Long reviewId) throws Exception {
        try {
            return reviewLikeRepository.findByReview_ReviewId(reviewId).stream()
                    .map(ReviewLikeResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public ResponseDto delete(Long reviewId) throws Exception{
        try {
            if(reviewId == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                Review review = reviewRepository.findById(reviewId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + reviewId));
                reviewRepository.delete(review);
                return new ResponseDto(200, "리뷰 삭제 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public ResponseDto dislike(ReviewLikeRequestDto requestDto) throws Exception{
        try {
            if(requestDto == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                ReviewLike like = reviewLikeRepository.findById(new ReviewLikeId(requestDto.getReviewId(),requestDto.getUserId()))
                        .orElseThrow(() ->
                                new IllegalArgumentException("해당 리뷰가 존재하지 않습니다. reviewId = " + requestDto.getReviewId()));
                reviewLikeRepository.delete(like);
                return new ResponseDto(200, "댓글 좋아요 취소 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
