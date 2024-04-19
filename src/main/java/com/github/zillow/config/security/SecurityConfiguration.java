//package com.github.zillow.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration
////        extends WebSecurityConfigurerAdapter
//                                            {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().sameOrigin()
//                .and()
//                .formLogin().disable()
//                .csrf().disable()
//                .httpBasic().disable()
//                .rememberMe().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//    }
//
////    @Bean
////    public JwtTokenProvider jwtTokenProvider() {
////        return new JwtTokenProvider();
////    }
////                .and()
////                .authorizeRequests()
////                .antMatchers("/resources/static/**", "/v1/api/sign/*").permitAll()
////                .antMatchers("/v1/api/air-reservation/*").hasRole("USER")
////                .and()
////                .exceptionHandling()
////                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
////                .accessDeniedHandler(new CustomerAccessDeniedHandler())
////                .and()
////                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}