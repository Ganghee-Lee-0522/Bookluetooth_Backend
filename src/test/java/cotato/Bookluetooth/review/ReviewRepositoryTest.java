package cotato.Bookluetooth.review;

import cotato.Bookluetooth.follow.Follow;
import cotato.Bookluetooth.follow.FollowRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
//@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    private Users user1;

    private Users user2;
    
    private Users user3;

    @BeforeEach
    public void setup(){
        user1 = userRepository.save(Users.builder()
            .userName("김철수")
            .userEmail("example1@example.com")
            .userImage("https://examplelink.example")
            .role(Role.USERS)
            .build());

        user2 = userRepository.save(Users.builder()
                .userName("김영희")
                .userEmail("example2@example.com")
                .userImage("https://examplelink.example")
                .role(Role.USERS)
                .build());

        user3 = userRepository.save(Users.builder()
                .userName("김짱구")
                .userEmail("example3@example.com")
                .userImage("https://examplelink.example")
                .role(Role.USERS)
                .build());
    }

    @AfterEach
    public void cleanup() {
        reviewRepository.deleteAll();
        userRepository.deleteAll();
        followRepository.deleteAll();
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
                .users(user1)
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
                .users(user1)
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
                .users(user1)
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
        assertThat(review.getUsers().getUserId()).isEqualTo(user1.getUserId());
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
                .users(user1)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());

        //when
        List<Review> reviewList = reviewRepository.findByUsers_UserId(user1.getUserId());

        //then
        Review review = reviewList.get(0);
        assertThat(review.getUsers().getUserId()).isEqualTo(user1.getUserId());
        assertThat(review.getBookIsbn()).isEqualTo(bookIsbn);
        assertThat(review.getReviewContent()).isEqualTo(reviewContent);
        assertThat((BigDecimal.valueOf(parseDouble(format.format(review.getBookPoint()))))).isEqualTo(bookPoint);
        assertThat(review.getBookTitle()).isEqualTo(bookTitle);
        assertThat(review.getBookImage()).isEqualTo(bookImage);
        assertThat(review.getBookAuthor()).isEqualTo(bookAuthor);
    }

    @Test
    public void 리뷰_팔로어리뷰조회(){
        //given
        DecimalFormat format = new DecimalFormat("#.##"); // 소수점 아래 2자리로 맞춰주기 위함

        String bookIsbn = "012-34-567890-1-2";
        String reviewContent = "Hello";
        BigDecimal bookPoint = BigDecimal.valueOf(4.2);
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        reviewRepository.save(Review.builder()
                .users(user1)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());

        reviewRepository.save(Review.builder()
                .users(user2)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());

        reviewRepository.save(Review.builder()
                .users(user3)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build());

        followRepository.save(new Follow(user1, user2));
        followRepository.save(new Follow(user3, user2));

        //when
        List<Review> reviewList = new ArrayList<>();

        Users user = userRepository.findByUserEmail(user2.getUserEmail()).get();

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
        //then
        assertThat(reviewList.size()).isEqualTo(2);
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
                .users(user1)
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