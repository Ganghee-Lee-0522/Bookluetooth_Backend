package cotato.Bookluetooth.comment.service;

import cotato.Bookluetooth.ResponseDto;
import cotato.Bookluetooth.comment.domain.Comment;
import cotato.Bookluetooth.comment.domain.CommentRepository;
import cotato.Bookluetooth.comment.dto.CommentListFormat;
import cotato.Bookluetooth.comment.dto.CommentPatchRequestDto;
import cotato.Bookluetooth.comment.dto.CommentPostRequestDto;
import cotato.Bookluetooth.comment.dto.CommentResponseDto;
import cotato.Bookluetooth.config.auth.SessionUser;
import cotato.Bookluetooth.review.Review;
import cotato.Bookluetooth.review.ReviewRepository;
import cotato.Bookluetooth.review.NewReviewRepository;
import cotato.Bookluetooth.users.domain.UserRepository;
import cotato.Bookluetooth.users.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;
    private final NewReviewRepository newReviewRepository;

    // 댓글 등록
    @Transactional
    public ResponseDto post(SessionUser sessionUser, CommentPostRequestDto commentPostRequestDto) throws Exception {
        try {
            Optional<Users> users = userRepository.findByUserEmail(sessionUser.getUserEmail());
            if (commentPostRequestDto.getCommentContent().isEmpty() || users.isEmpty()) {
                throw new Exception("필수 데이터 누락");
            }
            else {
                Optional<Review> review = newReviewRepository.findByReviewId(commentPostRequestDto.getReviewId().getReviewId());
                if (review.isEmpty()) {
                    throw new Exception("해당 리뷰가 존재하지 않음");
                }

                Comment comment = new Comment(commentPostRequestDto.getReviewId(), users.get(), commentPostRequestDto.getCommentContent());
                commentRepository.save(comment);
                return new ResponseDto(200, "댓글 등록 성공");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // 댓글 수정
    @Transactional
    public ResponseDto patch(SessionUser sessionUser, CommentPatchRequestDto commentPatchRequestDto) throws Exception {
        try {
            if(commentPatchRequestDto.getCommentContent().isEmpty()) {
                throw new Exception("필수 데이터 누락");
            } else {

                Optional<Comment> originalComment = commentRepository.findByCommentId(commentPatchRequestDto.getCommentId());
                if(originalComment.get().getReviewId() != commentPatchRequestDto.getReviewId()) {
                    throw new Exception("잘못된 요청");
                }

                commentRepository.save(originalComment.get().update(commentPatchRequestDto.getCommentContent()));
                return new ResponseDto(200, "댓글 수정 성공");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // 댓글 조회
    @Transactional(readOnly = true)
    public CommentResponseDto getAllComment(Long reviewId) throws Exception {
        try {
            Optional<Review> review = newReviewRepository.findByReviewId(reviewId);
            if(review.isEmpty()) {
                throw new Exception("존재하지 않는 리뷰 아이디");
            } else {
                List<Comment> comments = commentRepository.findByReviewId(reviewId);
                List<CommentListFormat> commentList = new ArrayList<>();
                for(Comment c : comments) {
                    if(commentRepository.findByCommentId(c.getCommentId()).isEmpty() || userRepository.findByUserId(c.getUserId()).isEmpty()) {
                        throw new Exception("유효하지 않은 댓글");
                    } else {
                        String userName = userRepository.findByUserId(c.getUserId()).get().getUserName();
                        String userImage = userRepository.findByUserId(c.getUserId()).get().getUserImage();
                        CommentListFormat commentListFormat = new CommentListFormat(c.getCommentId(), c.getReviewId(), userName, c.getModifiedDate(), c.getCommentContent(), userImage);
                        commentList.add(commentListFormat);
                    }
                }
                return new CommentResponseDto(commentList);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    // 댓글 삭제
}
