package com.fitzone.api.service;

import com.fitzone.api.dto.request.LoginRequest;
import com.fitzone.api.dto.request.RegisterRequest;
import com.fitzone.api.dto.response.AuthResponse;
import com.fitzone.api.model.Member;
import com.fitzone.api.model.Role;
import com.fitzone.api.model.User;
import com.fitzone.api.repository.MemberRepository;
import com.fitzone.api.repository.UserRepository;
import com.fitzone.api.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken!");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_MEMBER)
                .build();

        User savedUser = userRepository.save(user);

        Member member = Member.builder()
                .user(savedUser)
                .joinDate(LocalDate.now())
                // .membership(get from request later)
                .build();

        memberRepository.save(member);

        // Auto login after registration could be done here, or force them to login.
        // Let's generate a token directly.
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication, savedUser);

        return AuthResponse.builder()
                .token(jwt)
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwt = tokenProvider.generateToken(authentication, user);

        return AuthResponse.builder()
                .token(jwt)
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
