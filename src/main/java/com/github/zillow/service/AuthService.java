package com.github.zillow.service;

import com.github.zillow.config.security.JwtTokenProvider;
import com.github.zillow.repository.entity.UserEntity;
import com.github.zillow.repository.roles.Roles;
import com.github.zillow.repository.roles.RolesRepository;
import com.github.zillow.repository.user.UserRepository;
import com.github.zillow.repository.userPrincipal.UserPrincipal;
import com.github.zillow.repository.userPrincipal.UserPrincipalRepository;
import com.github.zillow.repository.userPrincipal.UserPrincipalRoles;
import com.github.zillow.repository.userPrincipal.UserPrincipalRolesRepository;
import com.github.zillow.service.exception.NotAcceptException;
import com.github.zillow.service.exception.NotFoundException;
import com.github.zillow.web.dto.auth.Login;
import com.github.zillow.web.dto.auth.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserPrincipalRepository userPrincipalRepository;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserPrincipalRolesRepository userPrincipalRolesRepository;
    //            (transactionManager = "tmJpa2")
    @Transactional
    public boolean signUp(SignUp signUpRequest) {
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        String name = signUpRequest.getName();


    // 1. 아이디 동일 체크
        if (userPrincipalRepository.existsByEmail(email)) {
        return false;
    }
    // 2. 유저가 있으면 ID 만 등록 아니면 유저도 만들기
    UserEntity userFound = userRepository.findByName(name)
            .orElseGet(() ->
                    userRepository.save(UserEntity.builder()
                            .name(name)
                            .build()));
    // 3. UserName Password 등록, 기본 ROLE_USER
    Roles roles = rolesRepository.findByName("ROLE_USER")
            .orElseThrow(() -> new NotFoundException("ROLE_USER를 찾을 수가 없습니다."));
    UserPrincipal userPrincipal = UserPrincipal.builder()
            .email(email)
            .user(userFound)
            .password(passwordEncoder.encode(password))
            .build();
        userPrincipalRepository.save(userPrincipal);
        userPrincipalRolesRepository.save(
                UserPrincipalRoles.builder()
                .roles(roles)
                .userPrincipal(userPrincipal)
                .build()
        );
        return true;
}


    public String login(Login loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            UserPrincipal userPrincipal = userPrincipalRepository.findByEmailFetchJoin(email)
                    .orElseThrow(() -> new NotFoundException("UserPrincipal을 찾을 수 없습니다."));

            List<String> roles = userPrincipal.getUserPrincipalRoles()
                    .stream()
                    .map(UserPrincipalRoles::getRoles)
                    .map(Roles::getName)
                    .collect(Collectors.toList());

            return jwtTokenProvider.createToken(email, roles);
        } catch (Exception e){
//            e.printStackTrace();
            throw new NotAcceptException("로그인 할 수 없습니다.");
        }
    }
}
