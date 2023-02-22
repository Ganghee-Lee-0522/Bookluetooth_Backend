package cotato.Bookluetooth.users.controller;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.config.auth.LoginUser;
import cotato.Bookluetooth.config.auth.OAuthAttributes;
import cotato.Bookluetooth.config.auth.SessionUser;
import cotato.Bookluetooth.users.dto.UserResponseDto;
import cotato.Bookluetooth.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    //private final OAuthAttributes oAuthAttributes;

    // 회원 정보 조회
    @GetMapping("/userinfo")
    public ResponseEntity getUserInfo(@LoginUser SessionUser users) {
        try {
            UserResponseDto userResponseDto = userService.getUserInfo(users);
            return ResponseEntity.ok().body(ResponseDto.response(200, "회원 정보 가져오기 성공", userResponseDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(400, "존재하지 않는 유저 아이디"));
        }
    }
    
    // 회원 이름 수정
    @PatchMapping("/userinfo/name")
    public ResponseEntity patchUserName(@LoginUser SessionUser users, @RequestBody String newName) {
        try {
            ResponseDto responseDto = userService.updateName(users, newName);
            return ResponseEntity.ok().body(ResponseDto.response(200, responseDto.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }

    }

    // 회원 사진 수정
    @PatchMapping("/userinfo/image")
    public ResponseEntity patchUserImage(@LoginUser SessionUser users, @RequestBody String newImage) {
        try {
            ResponseDto responseDto = userService.updateImage(users, newImage);
            return ResponseEntity.ok().body(ResponseDto.response(200, responseDto.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }

    }

    // 회원 탈퇴
    @DeleteMapping("/userinfo/unregister")
    public ResponseEntity deleteUser(@LoginUser SessionUser users) {
        try {
            ResponseDto responseDto = userService.unregister(users);
            return ResponseEntity.ok().body(ResponseDto.response(200, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    /*
    // 로그인
    @GetMapping("/")
    public ResponseEntity loginUser() {
        return ResponseEntity.ok().body(ResponseDto.response(200, "로그인 성공"));
    }

    // 로그아웃
    @GetMapping("/logoutSuccess")
    public ResponseEntity logoutUser() {
        return ResponseEntity.ok().body(ResponseDto.response(200, "로그아웃 성공"));
    }
     */
}
