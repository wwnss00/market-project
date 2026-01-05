package com.example.marketproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {  // OncePerRequestFilter: 요청당 한 번만 실행 보장

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Request Header에서 JWT 토큰 추출
        String token = resolveToken(request);

        // 2. 토큰 유효성 검증
        if (token != null && jwtTokenProvider.validateToken(token)) {

            // 3. 토큰에서 사용자 정보 추출
            Long userId = jwtTokenProvider.getUserId(token);
            String role = jwtTokenProvider.getRole(token);

            // 4. 권한 정보 생성
            List<SimpleGrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + role)
            );

            // 5. Authentication 객체 생성
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId,      // principal (주체)
                            null,        // credentials (비밀번호)
                            authorities  // authorities (권한)
                    );

            // 6. SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("인증 성공 - UserId: {}, Role: {}", userId, role);
        }

        // 7. 다음 필터로 전달
        filterChain.doFilter(request, response);
    }
    /**
     * Request Header에서 토큰 정보 추출
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // "Bearer " 제거
        }

        return null;

    }
}
