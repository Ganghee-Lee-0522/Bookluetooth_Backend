package cotato.Bookluetooth.users.service;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.config.auth.SessionUser;
import cotato.Bookluetooth.users.dto.UserResponseDto;
import cotato.Bookluetooth.users.domain.UserRepository;
import cotato.Bookluetooth.users.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

<<<<<<< HEAD
    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(SessionUser sessionUser) throws Exception {
        try {
            if(sessionUser.getUserEmail().isEmpty()) {
=======
    @Transactional
    public UserResponseDto getUserInfo(SessionUser sessionUser) throws Exception {
        try {
            if(sessionUser == null) {
>>>>>>> honey
                throw new Exception("존재하지 않는 사용자");
            }
            else {
                Optional<Users> users = userRepository.findByUserEmail(sessionUser.getUserEmail());
                if (users.isEmpty()) {
                    throw new Exception("존재하지 않는 사용자");
                }
                else {
                    return new UserResponseDto(users);
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public ResponseDto updateName(SessionUser sessionUser, String newName) throws Exception {
        try {
            if(newName == null) {
                throw new Exception("필수 데이터 누락");
            }
            else {
                Optional<Users> originalUser = userRepository.findByUserEmail(sessionUser.getUserEmail());

                if(originalUser.get().getUserName().equals(newName)) {
                    throw new Exception("잘못된 요청");
                }

                userRepository.save(originalUser.get().update(newName, originalUser.get().getUserImage()));
                return new ResponseDto(200, "회원 이름 수정 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // 이미지 파일 업로드 구현은 수정이 필요함
    // 현재는 파일 업로드가 아니라 url 수정만 들어가 있음
    @Transactional
    public ResponseDto updateImage(SessionUser sessionUser, String newImage) throws Exception {
        try {
            if(newImage == null) {
                throw new Exception("필수 데이터 누락");
            }
            else {
                Optional<Users> originalUser = userRepository.findByUserEmail(sessionUser.getUserEmail());

                if(originalUser.get().getUserImage().equals(newImage)) {
                    throw new Exception("잘못된 요청");
                }

                userRepository.save(originalUser.get().update(originalUser.get().getUserName(), newImage));
                return new ResponseDto(200, "회원 사진 수정 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDto unregister(SessionUser sessionUser) throws Exception {
        try {
            if (sessionUser == null) {
                throw new Exception("존재하지 않는 사용자");
            }
            else {
                Optional<Users> users = userRepository.findByUserEmail(sessionUser.getUserEmail());
                if (users.isEmpty()) {
                    throw new Exception("존재하지 않는 사용자");
                }
                else {
                    userRepository.deleteById(users.get().getUserId());
                    return new ResponseDto(200, "회원 탈퇴 성공");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
