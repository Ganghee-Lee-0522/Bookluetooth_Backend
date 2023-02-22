package cotato.Bookluetooth.review;

import cotato.Bookluetooth.users.domain.Role;
import cotato.Bookluetooth.users.domain.UserRepository;
import cotato.Bookluetooth.users.domain.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Double.parseDouble;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
//@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    private Users user;

    @BeforeEach
    public void setup(){
        user = userRepository.save(Users.builder()
            .userName("김철수")
            .userEmail("example@example.com")
            .userImage("https://examplelink.example")
            .role(Role.USERS)
            .build());
    }

    @AfterEach
    public void cleanup() {
        reviewRepository.deleteAll();
    }

    @Test
    public void 리뷰_저장후불러오기(){
        //given
        DecimalFormat format = new DecimalFormat("#.##"); // 소수점 아래 2자리로 맞춰주기 위함

        String bookIsbn = "012-34-567890-1-2";
        String reviewContent = "Hello";
        BigDecimal bookPoint = BigDecimal.valueOf(4.2);
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        reviewRepository.save(Review.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());

        //when
        List<Review> reviewList = reviewRepository.findAll();

        //then
        Review review = reviewList.get(0);
        assertThat(review.getBookIsbn()).isEqualTo(bookIsbn);
        assertThat(review.getReviewContent()).isEqualTo(reviewContent);
        assertThat((BigDecimal.valueOf(parseDouble(format.format(review.getBookPoint()))))).isEqualTo(bookPoint);
        assertThat(review.getBookTitle()).isEqualTo(bookTitle);
        assertThat(review.getBookImage()).isEqualTo(bookImage);
        assertThat(review.getBookAuthor()).isEqualTo(bookAuthor);
    }

    @Test
    public void 리뷰_저장후업데이트(){
        //given
        String bookIsbn = "012-34-567890-1-2";
        String reviewContent = "Hello";
        BigDecimal bookPoint = BigDecimal.valueOf(4.2);
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        String updateAuthor = "김영희";

        Review review = reviewRepository.save(Review.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());

        //when
        review.update(bookIsbn, reviewContent, bookPoint, bookTitle, bookImage, updateAuthor);

        //then
        assertThat(review.getBookAuthor()).isEqualTo(updateAuthor);
    }
    @Test
    public void 리뷰_ReviewId로조회(){
        //given
        DecimalFormat format = new DecimalFormat("#.##"); // 소수점 아래 2자리로 맞춰주기 위함

        String bookIsbn = "012-34-567890-1-2";
        String reviewContent = "Hello";
        BigDecimal bookPoint = BigDecimal.valueOf(4.2);
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        Review saveReview = reviewRepository.save(Review.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());

        //when
        Review review = reviewRepository.findById(saveReview.getReviewId()).get();

        //then
        assertThat(review.getUsers().getUserId()).isEqualTo(user.getUserId());
        assertThat(review.getBookIsbn()).isEqualTo(bookIsbn);
        assertThat(review.getReviewContent()).isEqualTo(reviewContent);
        assertThat((BigDecimal.valueOf(parseDouble(format.format(review.getBookPoint()))))).isEqualTo(bookPoint);
        assertThat(review.getBookTitle()).isEqualTo(bookTitle);
        assertThat(review.getBookImage()).isEqualTo(bookImage);
        assertThat(review.getBookAuthor()).isEqualTo(bookAuthor);
    }

    @Test
    public void 리뷰_UserId로조회(){
        //given
        DecimalFormat format = new DecimalFormat("#.##"); // 소수점 아래 2자리로 맞춰주기 위함

        String bookIsbn = "012-34-567890-1-2";
        String reviewContent = "Hello";
        BigDecimal bookPoint = BigDecimal.valueOf(4.2);
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        reviewRepository.save(Review.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());

        //when
        List<Review> reviewList = reviewRepository.findByUsers_UserId(user.getUserId());

        //then
        Review review = reviewList.get(0);
        assertThat(review.getUsers().getUserId()).isEqualTo(user.getUserId());
        assertThat(review.getBookIsbn()).isEqualTo(bookIsbn);
        assertThat(review.getReviewContent()).isEqualTo(reviewContent);
        assertThat((BigDecimal.valueOf(parseDouble(format.format(review.getBookPoint()))))).isEqualTo(bookPoint);
        assertThat(review.getBookTitle()).isEqualTo(bookTitle);
        assertThat(review.getBookImage()).isEqualTo(bookImage);
        assertThat(review.getBookAuthor()).isEqualTo(bookAuthor);
    }

    @Test
    public void 리뷰_삭제(){
        //given
        String bookIsbn = "012-34-567890-1-2";
        String reviewContent = "Hello";
        BigDecimal bookPoint = BigDecimal.valueOf(4.2);
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        Review savedReview = reviewRepository.save(Review.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());


        //when
        Review review = reviewRepository.findById(savedReview.getReviewId()).get();
        reviewRepository.delete(review);
        List<Review> reviewList = reviewRepository.findAll();

        //then
        assertThat(reviewList.size()).isEqualTo(0);
    }
}