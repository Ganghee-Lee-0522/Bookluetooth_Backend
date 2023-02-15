package cotato.Bookluetooth.wishlist;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishlistRequestDto {

    private Long userId;
    private String bookIsbn;
    private String bookTitle;
    private String bookImage;
    private String bookAuthor;

    @Builder
    public WishlistRequestDto(Wishlist wishlist) {
        this.userId = wishlist.getUserId();
        this.bookIsbn = wishlist.getBookIsbn();
        this.bookTitle = wishlist.getBookTitle();
        this.bookImage = wishlist.getBookImage();
        this.bookAuthor = wishlist.getBookAuthor();
    }

    public Wishlist toEntity() {
        return Wishlist.builder()
                .userId(userId)
                .bookIsbn(bookIsbn)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor)
                .build();
    }
}
