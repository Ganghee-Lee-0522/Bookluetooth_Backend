/*
package cotato.Bookluetooth.comment;

import cotato.Bookluetooth.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    @Column(nullable = false)
    private Review reviewId;
    //@OneToMany(mappedBy = "reviewComment")를 Review의 reviewId에 추가할 것

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Column(nullable = false)
    private Users userId;

    @Column(nullable = false)
    private String commentContent;

    @Builder
    public Comment(Review reviewId, Users userId, String commentContent) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.commentContent = commentContent;
    }
}
*/