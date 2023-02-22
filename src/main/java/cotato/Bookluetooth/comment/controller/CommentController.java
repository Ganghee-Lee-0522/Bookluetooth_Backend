package cotato.Bookluetooth.comment.controller;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.comment.dto.CommentPatchRequestDto;
import cotato.Bookluetooth.comment.dto.CommentPostRequestDto;
import cotato.Bookluetooth.comment.dto.CommentResponseDto;
import cotato.Bookluetooth.comment.service.CommentService;
import cotato.Bookluetooth.config.auth.LoginUser;
import cotato.Bookluetooth.config.auth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/post")
    public ResponseEntity postComment(@LoginUser SessionUser users, @RequestBody CommentPostRequestDto requestDto) {
        try {
            ResponseDto responseDto = commentService.post(users, requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(200, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 댓글 수정
    @PatchMapping("/patch")
    public ResponseEntity patchComment(@LoginUser SessionUser users, @RequestBody CommentPatchRequestDto requestDto) {
        try {
            ResponseDto responseDto = commentService.patch(users, requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(200, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 댓글 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity getCommentList(@PathVariable("reviewId") Long reviewId) {
        try {
            CommentResponseDto responseDto = commentService.getAllComment(reviewId);
            return ResponseEntity.ok().body(ResponseDto.response(200, "댓글 조회 성공", responseDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(400, e.getMessage()));
        }
    }

    // 댓글 삭제
    //@DeleteMapping("/delete/{commentId}")
    //public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId) {
   //     try {
   //         CommentReques
    //    }
   // }
}
