package cotato.Bookluetooth.wishlist;

import lombok.Getter;

@Getter
public class WishlistResponseDto {
    private Long wishlistId;
    private Long userId;
    private String bookIsbn;
    private String bookTitle;
    private String bookImage;
    private String bookAuthor;

    public WishlistResponseDto(Wishlist wishlist){
        this.wishlistId = wishlist.getWishlistId();
        this.userId = wishlist.getUserId();
        this.bookIsbn = wishlist.getBookIsbn();
        this.bookTitle = wishlist.getBookTitle();
        this.bookImage = wishlist.getBookImage();
        this.bookAuthor = wishlist.getBookAuthor();
    }
}
