package cotato.Bookluetooth.users.dto;

import cotato.Bookluetooth.users.domain.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class UserResponseDto {
    private String userName;
    private String userEmail;
    private String userImage;

    @Builder
    public UserResponseDto(Optional<Users> users) {
        this.userName = users.get().getUserName();
        this.userEmail = users.get().getUserEmail();
        this.userImage = users.get().getUserImage();
    }
}
