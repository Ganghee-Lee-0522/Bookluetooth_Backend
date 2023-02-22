package cotato.Bookluetooth.review;

import cotato.Bookluetooth.BaseTimeEntity;
<<<<<<< HEAD
=======
import cotato.Bookluetooth.users.domain.Users;
>>>>>>> honey
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;

    @Column(length = 20, nullable = false)
    private String bookIsbn;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String reviewContent;

    @Column(columnDefinition = "DECIMAL(3,2) DEFAULT (0.00)")
    private BigDecimal bookPoint;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String bookImage;

    @Column(nullable = false)
    private String bookAuthor;

    @Builder
    public Review(Users users, String bookIsbn, String reviewContent, BigDecimal bookPoint,
                  String bookTitle, String bookImage, String bookAuthor){
        this.users = users;
        this.bookIsbn = bookIsbn;
        this.reviewContent = reviewContent;
        this.bookPoint = bookPoint;
        this.bookTitle = bookTitle;
        this.bookImage = bookImage;
        this.bookAuthor = bookAuthor;
    }

    public void update(String bookIsbn, String reviewContent, BigDecimal bookPoint,
                       String bookTitle, String bookImage, String bookAuthor) {
        this.bookIsbn = bookIsbn;
        this.reviewContent = reviewContent;
        this.bookPoint = bookPoint;
        this.bookTitle = bookTitle;
        this.bookImage = bookImage;
        this.bookAuthor = bookAuthor;
    }
}
