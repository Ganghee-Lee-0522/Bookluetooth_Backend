package cotato.Bookluetooth.review;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewResponseDto {
    private Long reviewId;
    private Long userId;
    private String bookIsbn;
    private String reviewContent;
    private BigDecimal bookPoint;
    private String bookTitle;
    private String bookImage;
    private String bookAuthor;

    public ReviewResponseDto(Review review) {
        this.reviewId = review.getReviewId();
        this.userId = review.getUserId();
        this.bookIsbn = review.getBookIsbn();
        this.reviewContent = review.getReviewContent();
        this.bookPoint = review.getBookPoint();
        this.bookTitle = review.getBookTitle();
        this.bookImage = review.getBookImage();
        this.bookAuthor = review.getBookAuthor();
    }
}
