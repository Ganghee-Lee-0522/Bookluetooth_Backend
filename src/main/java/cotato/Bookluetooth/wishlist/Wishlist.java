package cotato.Bookluetooth.wishlist;

import cotato.Bookluetooth.BaseTimeEntity;
import cotato.Bookluetooth.users.domain.Users;
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

    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;

    @Column(nullable = false)
    private String bookIsbn;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String bookImage;

    @Column(nullable = false)
    private String bookAuthor;

    @Builder
    public Wishlist(Users users, String bookIsbn, String bookTitle,
                    String bookImage, String bookAuthor){
        this.users = users;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.bookImage = bookImage;
        this.bookAuthor = bookAuthor;
    }
}
