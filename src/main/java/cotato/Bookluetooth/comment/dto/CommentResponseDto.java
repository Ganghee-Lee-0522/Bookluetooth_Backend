package cotato.Bookluetooth.comment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class CommentResponseDto {
    List<CommentListFormat> commentList;

    @Builder
    public CommentResponseDto(List<CommentListFormat> commentList) {
        this.commentList = commentList;
    }

}
