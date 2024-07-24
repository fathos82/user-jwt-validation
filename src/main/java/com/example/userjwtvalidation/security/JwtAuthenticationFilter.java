package com.example.userjwtvalidation.security;

import com.example.userjwtvalidation.user.User;
import com.example.userjwtvalidation.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = extractTokenFromRequest(request);

        try {
            if (jwtToken == null) {
                throw new BadCredentialsException("Token JWT missing.");
            }

            // Validar e decodificar o token JWT
            String userName = jwtService.validateTokenAndReturnSubject(jwtToken);
            if (userName == null) {
                throw new BadCredentialsException("Token JWT invalid or expired");
            }

            // Obter informações adicionais do usuário, se necessário
            User user = userRepository.findByUsername(userName)
                    .orElseThrow(() -> new BadCredentialsException("User not found "));

            // Construir objeto de autenticação
            SecurityUser securityUser = new SecurityUser(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (BadCredentialsException e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/auth");
    }
}
