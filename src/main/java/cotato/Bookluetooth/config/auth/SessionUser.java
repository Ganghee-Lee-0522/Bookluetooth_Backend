package cotato.Bookluetooth.config.auth;

import cotato.Bookluetooth.users.domain.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String userName;
    private String userEmail;
    private String userImage;

    public SessionUser(Users users) {
        this.userName = users.getUserName();
        this.userEmail = users.getUserEmail();
        this.userImage = users.getUserImage();
    }
}
