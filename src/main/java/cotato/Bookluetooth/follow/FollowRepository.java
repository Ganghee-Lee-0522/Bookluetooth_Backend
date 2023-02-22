package cotato.Bookluetooth.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findByFollowerUser_UserIdAndFolloweeUser_UserId(Long followerUserId, Long followeeUserId);

    List<Follow> findByFolloweeUser_UserId(Long followeeUserId);

    List<Follow> findByFollowerUser_UserId(Long followeeUserId);
}
