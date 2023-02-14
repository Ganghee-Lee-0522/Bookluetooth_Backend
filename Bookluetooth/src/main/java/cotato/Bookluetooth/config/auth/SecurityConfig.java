package cotato.Bookluetooth.config.auth;

import cotato.Bookluetooth.users.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면 사용을 위한 것
                .and()
                    .authorizeRequests() // url별 권한관리
                    .antMatchers("/", "/h2-console/**").permitAll()
                    .antMatchers("/comment/**", "/review/**", "/wishlist/**", "/follow/**", "/follower/**",
                            "/followee/**", "/userinfo/**").hasRole(Role.USERS.name())
                    .antMatchers().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                .userService((OAuth2UserService<OAuth2UserRequest, OAuth2User>) customOAuth2UserService);

        return http.build();
    }
}
