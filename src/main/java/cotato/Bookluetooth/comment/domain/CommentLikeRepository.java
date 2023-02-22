package cotato.Bookluetooth.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {
    List<CommentLike> findByComment_CommentId(Long commentId);
}
