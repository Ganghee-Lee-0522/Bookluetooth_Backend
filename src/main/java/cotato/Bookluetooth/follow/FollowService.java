package cotato.Bookluetooth.follow;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.config.auth.SessionUser;
import cotato.Bookluetooth.users.domain.UserRepository;
import cotato.Bookluetooth.users.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public ResponseDto saveFollow(FollowRequestDto requestDto) throws Exception{
        try {
            if(requestDto == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                Long followerId = requestDto.getFollowerId();
                Users followerUser = userRepository.findById(followerId)
                        .orElseThrow(() -> new IllegalArgumentException("팔로워 할 유저가 존재하지 않습니다. userId = " + followerId));
                Long followeeId = requestDto.getFolloweeId();
                Users followeeUser = userRepository.findById(followeeId)
                        .orElseThrow(()-> new IllegalArgumentException("유저 ID 오류, userId = " + followeeId));
                followRepository.save(Follow.builder().followerUser(followerUser)
                        .followeeUser(followeeUser).build());
                return new ResponseDto(200, "팔로우 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public ResponseDto deleteFollow(FollowRequestDto requestDto) throws Exception{
        try {
            if(requestDto == null) {
                throw new IllegalArgumentException("필수 데이터 누락");
            }
            else {
                Long followerId = requestDto.getFollowerId();
                Users followerUser = userRepository.findById(followerId)
                        .orElseThrow(() -> new IllegalArgumentException("팔로워 취소 할 유저가 존재하지 않습니다. userId = " + followerId));
                Long followeeId = requestDto.getFolloweeId();
                Users followeeUser = userRepository.findById(followeeId)
                        .orElseThrow(()-> new IllegalArgumentException("유저 ID 오류, userId = " + followeeId));
                Follow follow = followRepository.findByFollowerUser_UserIdAndFolloweeUser_UserId(followerUser.getUserId(),
                        followeeUser.getUserId());
                followRepository.delete(follow);
                return new ResponseDto(200, "팔로우 끊기 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<FollowResponseDto> findMyFollower(SessionUser users) throws Exception{
        try {
            Users user = userRepository.findByUserEmail(users.getUserImage()).get();
            return followRepository.findByFolloweeUser_UserId(user.getUserId()).stream()
                    .map(FollowResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<FollowResponseDto> findFollowerByUserId(Long userId) throws Exception{
        try {
            Users user = userRepository.findById(userId).get();
            return followRepository.findByFolloweeUser_UserId(user.getUserId()).stream()
                    .map(FollowResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<FollowResponseDto> findMyFollowee(SessionUser users) throws Exception{
        try {
            Users user = userRepository.findByUserEmail(users.getUserImage()).get();
            return followRepository.findByFollowerUser_UserId(user.getUserId()).stream()
                    .map(FollowResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<FollowResponseDto> findFolloweeByUserId(Long userId) throws Exception {
        try {
            Users user = userRepository.findById(userId).get();
            return followRepository.findByFollowerUser_UserId(user.getUserId()).stream()
                    .map(FollowResponseDto::new)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
