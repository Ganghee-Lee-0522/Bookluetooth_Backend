package cotato.Bookluetooth.wishlist;

import cotato.Bookluetooth.review.Review;
import cotato.Bookluetooth.users.domain.Role;
import cotato.Bookluetooth.users.domain.UserRepository;
import cotato.Bookluetooth.users.domain.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WishlistRepositoryTest {
    @Autowired
    private WishlistRepository wishlistRepository;
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
        wishlistRepository.deleteAll();
    }

    @Test
    public void 위시리스트_저장후불러오기(){
        //given
        String bookIsbn = "012-34-567890-1-2";
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        wishlistRepository.save(Wishlist.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor).build());

        //when
        List<Wishlist> wishlistList = wishlistRepository.findAll();

        //then
        Wishlist wishlist = wishlistList.get(0);
        assertThat(wishlist.getBookIsbn()).isEqualTo(bookIsbn);
        assertThat(wishlist.getBookTitle()).isEqualTo(bookTitle);
        assertThat(wishlist.getBookImage()).isEqualTo(bookImage);
        assertThat(wishlist.getBookAuthor()).isEqualTo(bookAuthor);
    }

    @Test
    public void 위시리스트_불러오기(){
        //given
        String bookIsbn = "012-34-567890-1-2";
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        wishlistRepository.save(Wishlist.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor).build());

        //when
        List<Wishlist> wishlistList = wishlistRepository.findByUsers_UserId(user.getUserId());

        //then
        Wishlist wishlist = wishlistList.get(0);
        assertThat(wishlist.getBookIsbn()).isEqualTo(bookIsbn);
        assertThat(wishlist.getBookTitle()).isEqualTo(bookTitle);
        assertThat(wishlist.getBookImage()).isEqualTo(bookImage);
        assertThat(wishlist.getBookAuthor()).isEqualTo(bookAuthor);
    }

    @Test
    public void 위시리스트_삭제(){
        //given
        String bookIsbn = "012-34-567890-1-2";
        String bookTitle = "Hello";
        String bookImage = "https://example.com";
        String bookAuthor = "김철수";

        Wishlist savedWishlist = wishlistRepository.save(Wishlist.builder()
                .users(user)
                .bookIsbn(bookIsbn)
                .bookTitle(bookTitle)
                .bookImage(bookImage)
                .bookAuthor(bookAuthor).build());

        //when
        Wishlist wishlist = wishlistRepository.findByUsers_UserId(user.getUserId()).get(0);
        wishlistRepository.delete(wishlist);
        List<Wishlist> wishlistList = wishlistRepository.findAll();

        //then
        assertThat(wishlistList.size()).isEqualTo(0);

    }
}