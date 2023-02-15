package cotato.Bookluetooth.users.dto;

import cotato.Bookluetooth.users.domain.Role;
import cotato.Bookluetooth.users.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String userName;
    private String userEmail;
    private String userImage;
    private Role role;

    @Builder
    public UserRequestDto(Users users) {
        this.userName = users.getUserName();
        this.userEmail = users.getUserEmail();
        this.userImage = users.getUserImage();
        this.role = users.getRole();
    }

    public Users toEntity() {
        return Users.builder()
                .userName(userName)
                .userEmail(userEmail)
                .userImage(userImage)
                .role(role)
                .build();
    }
}
