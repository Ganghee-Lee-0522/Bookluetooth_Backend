package cotato.Bookluetooth.follow;

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
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "followerId")
    private Users followerUser;

    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "followeeId")
    private Users followeeUser;

    @Builder
    public Follow(Users followerUser, Users followeeUser){
        this.followerUser = followerUser;
        this.followeeUser = followeeUser;
    }
}
