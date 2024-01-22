package fr.nsurget.game_review.configuration;

import fr.nsurget.game_review.mapping.UrlRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth ->
                auth
                        .requestMatchers("/**").permitAll()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage(UrlRoute.URL_LOGIN)
                    .permitAll()
            )
            .logout(logout ->
                 logout
                    .logoutSuccessUrl(UrlRoute.URL_LOGIN)
                    .permitAll()
            );

        return http.build();
    }

}