package cotato.Bookluetooth.users.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userEmail;

    @Column
    private String userImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Users(String userName, String userEmail, String userImage, Role role) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.role = role;
    }

    public Users update(String userName, String userImage) {
        this.userName = userName;
        this.userImage = userImage;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
