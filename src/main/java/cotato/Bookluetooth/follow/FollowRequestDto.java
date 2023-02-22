package cotato.Bookluetooth.follow;

import cotato.Bookluetooth.users.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class FollowRequestDto {
    private Long followerId;
    private Long followeeId;

}
