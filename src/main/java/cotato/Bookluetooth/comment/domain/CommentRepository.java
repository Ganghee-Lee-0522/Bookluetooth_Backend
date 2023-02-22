package cotato.Bookluetooth.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByCommentId(Long commentId);

    List<Comment> findByReviewId(Long reviewId);
}
