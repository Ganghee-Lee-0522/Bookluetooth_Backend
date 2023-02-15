package cotato.Bookluetooth.wishlist;

import cotato.Bookluetooth.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Wishlist extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    // TODO : userId 외래키로 받아오기
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String bookIsbn;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String bookImage;

    @Column(nullable = false)
    private String bookAuthor;

    @Builder
    public Wishlist(Long userId, String bookIsbn, String bookTitle,
                    String bookImage, String bookAuthor){
        this.userId = userId;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.bookImage = bookImage;
        this.bookAuthor = bookAuthor;
    }
}
