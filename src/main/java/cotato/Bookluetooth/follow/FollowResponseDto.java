package cotato.Bookluetooth.follow;

import cotato.Bookluetooth.users.domain.Users;
import lombok.Getter;

@Getter
public class FollowResponseDto {
    private Long followerId;
    private Long followeeId;

    public FollowResponseDto(Follow follow){
        this.followerId = follow.getFollowerUser().getUserId();
        this.followeeId = follow.getFolloweeUser().getUserId();
    }
}
