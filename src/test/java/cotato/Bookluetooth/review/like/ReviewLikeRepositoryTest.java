package cotato.Bookluetooth.review.like;

import cotato.Bookluetooth.review.Review;
import cotato.Bookluetooth.review.ReviewRepository;
import cotato.Bookluetooth.users.domain.Role;
import cotato.Bookluetooth.users.domain.UserRepository;
import cotato.Bookluetooth.users.domain.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReviewLikeRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    private Users user;

    private Review review;

    @BeforeEach
    public void setup(){
        user = userRepository.save(Users.builder()
                .userName("김철수")
                .userEmail("example@example.com")
                .userImage("https://examplelink.example")
                .role(Role.USERS)
                .build());

        String bookIsbn = "012-34-567890-1-2";
        String reviewContent = "Hello";
        BigDecimal bookPoint = BigDecimal.valueOf(4.2);
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        review = reviewRepository.save(Review.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());
    }

    @AfterEach
    public void cleanup() {
        reviewRepository.deleteAll();
    }

    @Test
    public void 리뷰좋아요_등록후불러오기(){
        //given
        reviewLikeRepository.save(new ReviewLike(review, user));

        //when
        List<ReviewLike> reviewLikeList = reviewLikeRepository.findAll();

        //then
        ReviewLike reviewLike = reviewLikeList.get(0);
        assertThat(reviewLike.getReview().getReviewId()).isEqualTo(review.getReviewId());
        assertThat(reviewLike.getUsers().getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    public void 리뷰좋아요_ReviewId로조회(){
        //given
        ReviewLike newReviewLike = new ReviewLike(review, user);
        reviewLikeRepository.save(newReviewLike);

        //when
        List<ReviewLike> reviewLikeList = reviewLikeRepository.findByReview_ReviewId(newReviewLike.getReview().getReviewId());

        //then
        ReviewLike reviewLike = reviewLikeList.get(0);
        assertThat(reviewLike.getReview().getReviewId()).isEqualTo(review.getReviewId());
        assertThat(reviewLike.getUsers().getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    public void 리뷰좋아요_삭제(){
        //given
        ReviewLike savedReviewLike = reviewLikeRepository.save(new ReviewLike(review, user));

        //when
        ReviewLike reviewLike = reviewLikeRepository.findByReview_ReviewId(savedReviewLike
                .getReview().getReviewId()).get(0);
        reviewLikeRepository.delete(reviewLike);
        List<ReviewLike> reviewLikeList = reviewLikeRepository.findAll();

        //then
        assertThat(reviewLikeList.size()).isEqualTo(0);
    }
}