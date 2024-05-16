package com.github.zillow.config.security;

import com.github.zillow.web.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {

//    private static final String[] PERMIT_URL = {"/api/**", "/api/search/**"};
//    private static final Object USER_URL = {};
//    private static final String ADMIN_URL = ;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.disable())
                        .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .formLogin((auth)->auth.disable()).rememberMe((remember)->remember.disable())
                        .authorizeHttpRequests((auth)->auth
                                            .requestMatchers("/resources/static/**"/*와일드카드로넣어줌*/,"/api/sign/*").permitAll()
                                            .requestMatchers("/api/**","/api/search/**","api/interest/**").hasRole("USER"))
//                                            .requestMatchers(ADMIN_URL).hasRole("ADMIN"))
//                        .exceptionHandling()
//                            .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                            .accessDeniedHandler(new CustomerAccessDeniedHandler())
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        log.info("security filter실행");

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}