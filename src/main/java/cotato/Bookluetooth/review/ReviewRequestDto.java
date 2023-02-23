package cotato.Bookluetooth.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {

    private String bookIsbn;
    private String reviewContent;
    private BigDecimal bookPoint;
    private String bookTitle;
    private String bookImage;
    private String bookAuthor;

    @Builder
    public ReviewRequestDto(Review review) {
        this.bookIsbn = review.getBookIsbn();
        this.reviewContent = review.getReviewContent();
        this.bookPoint = review.getBookPoint();
        this.bookTitle = review.getBookTitle();
        this.bookImage = review.getBookImage();
        this.bookAuthor = review.getBookAuthor();
    }
}
