package cotato.Bookluetooth.users.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USERS("ROLE_USERS", "일반 사용자");

    private final String key;
    private final String title;
}
