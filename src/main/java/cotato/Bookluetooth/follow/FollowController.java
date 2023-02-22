package cotato.Bookluetooth.follow;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.config.auth.LoginUser;
import cotato.Bookluetooth.config.auth.SessionUser;
import cotato.Bookluetooth.review.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("follow")
public class FollowController {

    private final FollowService followService;

    // 팔로우 맺기
    @PostMapping("/post")
    public ResponseEntity<ResponseDto<Object>> postFollow(@RequestBody FollowRequestDto requestDto) {
        try{
            ResponseDto responseDto = followService.saveFollow(requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 팔로워 조회 (내 팔로워만)
    @GetMapping("/myfollower")
    public ResponseEntity<ResponseDto<List<FollowResponseDto>>> getMyFollower(@LoginUser SessionUser users){
        try {
            List<FollowResponseDto> followResponseDto = followService.findMyFollower(users);
            return ResponseEntity.ok().body(ResponseDto.response(200, "내 팔로워 정보 가져오기 성공",
                    followResponseDto));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 팔로워 조회
    @GetMapping("/follower/{userId}")
    public ResponseEntity<ResponseDto<List<FollowResponseDto>>> getFollowerByUserId(@PathVariable Long userId){
        try {
            List<FollowResponseDto> followResponseDto = followService.findFollowerByUserId(userId);
            return ResponseEntity.ok().body(ResponseDto.response(200, "팔로워 정보 가져오기 성공",
                    followResponseDto));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 팔로이 조회 (내 팔로이만)
    @GetMapping("/myfollowee")
    public ResponseEntity<ResponseDto<List<FollowResponseDto>>> getMyFollowee(@LoginUser SessionUser users){
        try {
            List<FollowResponseDto> followResponseDto = followService.findMyFollowee(users);
            return ResponseEntity.ok().body(ResponseDto.response(200, "내 팔로이 정보 가져오기 성공",
                    followResponseDto));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 팔로이 조회
    @GetMapping("/followee/{userId}")
    public ResponseEntity<ResponseDto<List<FollowResponseDto>>> getFolloweeByUserId(@PathVariable Long userId){
        try {
            List<FollowResponseDto> followResponseDto = followService.findFolloweeByUserId(userId);
            return ResponseEntity.ok().body(ResponseDto.response(200, "팔로워 정보 가져오기 성공",
                    followResponseDto));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 팔로우 끊기
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<Object>> deleteFollow(@RequestBody FollowRequestDto requestDto) {
        try{
            ResponseDto responseDto = followService.deleteFollow(requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(responseDto.getStatus(), responseDto.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }
}
