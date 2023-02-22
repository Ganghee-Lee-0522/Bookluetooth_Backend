package cotato.Bookluetooth.config.auth;

import cotato.Bookluetooth.users.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
<<<<<<< HEAD
import org.springframework.security.config.annotation.web.builders.WebSecurity;
=======
>>>>>>> honey
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
<<<<<<< HEAD
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;
=======
>>>>>>> honey

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
<<<<<<< HEAD
                .csrf().ignoringAntMatchers("/h2-console/**").disable()
                .headers()
                    .frameOptions().disable() // h2-console 화면 사용을 위한 것
                .and()
                    .authorizeRequests() // url별 권한관리
                    .antMatchers("/logout", "/h2-console/**", "/**").permitAll()
=======
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면 사용을 위한 것
                .and()
                    .authorizeRequests() // url별 권한관리
                    .antMatchers("/logout", "/h2-console/**").permitAll()
>>>>>>> honey
                    .antMatchers("/comment/**", "/review/**", "/wishlist/**", "/follow/**", "/follower/**",
                            "/followee/**", "/userinfo/**").hasRole(Role.USERS.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/oauth2/authorization/google")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                .userService((OAuth2UserService<OAuth2UserRequest, OAuth2User>) customOAuth2UserService);

        return http.build();
    }
}
