package cotato.Bookluetooth.follow;


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
class FollowRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    private Users user1;

    private Users user2;

    @BeforeEach
    public void setup(){
        user1 = userRepository.save(Users.builder()
                .userName("김철수")
                .userEmail("example@example.com")
                .userImage("https://examplelink.example")
                .role(Role.USERS)
                .build());

        user2 = userRepository.save(Users.builder()
                .userName("김영희")
                .userEmail("example@example.com")
                .userImage("https://examplelink.example")
                .role(Role.USERS)
                .build());
    }

    @AfterEach
    public void cleanup() {
        followRepository.deleteAll();
    }

    @Test
    public void 팔로우_등록후불러오기(){
        //given
        followRepository.save(new Follow(user1, user2));

        //when
        List<Follow> followList = followRepository.findAll();

        //then
        Follow follow = followList.get(0);
        assertThat(follow.getFollowId()).isEqualTo(1);
        assertThat(follow.getFollowerUser()).isEqualTo(user1);
        assertThat(follow.getFolloweeUser()).isEqualTo(user2);
    }

    @Test
    public void 팔로우_팔로어ID와팔로이ID로불러오기(){
        //given
        followRepository.save(new Follow(user1, user2));

        //when
        Follow follow = followRepository.findByFollowerUser_UserIdAndFolloweeUser_UserId(user1.getUserId(),
                user2.getUserId());

        //then
        assertThat(follow.getFollowerUser()).isEqualTo(user1);
        assertThat(follow.getFolloweeUser()).isEqualTo(user2);
    }

    @Test
    public void 팔로우_팔로어불러오기(){
        //given
        followRepository.save(new Follow(user1, user2));

        //when
        List<Follow> followList = followRepository.findByFolloweeUser_UserId(user2.getUserId());

        //then
        Follow follow = followList.get(0);
        assertThat(follow.getFollowerUser()).isEqualTo(user1);
    }

    @Test
    public void 팔로우_팔로이불러오기(){
        //given
        followRepository.save(new Follow(user1, user2));

        //when
        List<Follow> followList = followRepository.findByFollowerUser_UserId(user1.getUserId());

        //then
        Follow follow = followList.get(0);
        assertThat(follow.getFolloweeUser()).isEqualTo(user2);
    }

    @Test
    public void 팔로우_삭제(){
        //given
        followRepository.save(new Follow(user1, user2));


        //when
        Follow follow = followRepository.findByFollowerUser_UserIdAndFolloweeUser_UserId(user1.getUserId(),
                user2.getUserId());
        followRepository.delete(follow);
        List<Follow> followList = followRepository.findAll();

        //then
        assertThat(followList.size()).isEqualTo(0);
    }
}