package cotato.Bookluetooth.config.auth;

import cotato.Bookluetooth.users.domain.Role;
import cotato.Bookluetooth.users.domain.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String userName;
    private String userEmail;
    private String userImage;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String userName,
                           String userEmail, String userImage) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userImage = userImage;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
<<<<<<< HEAD
                .userName((String) attributes.get("userName"))
                .userEmail((String) attributes.get("userEmail"))
                .userImage((String) attributes.get("userImage"))
=======
                .userName((String) attributes.get("name"))
                .userEmail((String) attributes.get("email"))
                .userImage((String) attributes.get("image"))
>>>>>>> honey
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Users toEntity() {
        return Users.builder()
                .userName(userName)
                .userEmail(userEmail)
                .userImage(userImage)
                .role(Role.GUEST)
                .build();
    }
}
