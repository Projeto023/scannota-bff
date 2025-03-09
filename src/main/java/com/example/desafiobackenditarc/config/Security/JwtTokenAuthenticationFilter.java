package com.example.desafiobackenditarc.config.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtTokenAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtConfig jwtConfig;

    public JwtTokenAuthenticationFilter(final AuthenticationManager authenticationManager,
                                        final JwtConfig jwtConfig) {
        super(authenticationManager);
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(jwtConfig.getHeader());

        if (header != null && header.startsWith(jwtConfig.getPrefix())) {
            final String token = header.replace(jwtConfig.getPrefix(), "");

            try {
                final Claims claims = Jwts.parser()
                        .setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(token)
                        .getBody();

                final String username = claims.getSubject();
                if (username != null) {
                    final UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(username, null,
                                    Arrays.asList(new SimpleGrantedAuthority("admin")));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch (final Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }

}
