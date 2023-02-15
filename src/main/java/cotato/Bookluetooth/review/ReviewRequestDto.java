package cotato.Bookluetooth.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {

    private Long userId;
    private String bookIsbn;
    private String reviewContent;
    private BigDecimal bookPoint;
    private String bookTitle;
    private String bookImage;
    private String bookAuthor;

    @Builder
    public ReviewRequestDto(Review review) {
        this.userId = review.getUserId();
        this.bookIsbn = review.getBookIsbn();
        this.reviewContent = review.getReviewContent();
        this.bookPoint = review.getBookPoint();
        this.bookTitle = review.getBookTitle();
        this.bookImage = review.getBookImage();
        this.bookAuthor = review.getBookAuthor();
    }

    public Review toEntity() {
        return Review.builder()
                .userId(userId)
                .bookIsbn(bookIsbn)
                .reviewContent(reviewContent)
                .bookPoint(bookPoint)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build();
    }
}
