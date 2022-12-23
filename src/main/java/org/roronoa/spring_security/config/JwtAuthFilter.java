package org.roronoa.spring_security.config;
import lombok.RequiredArgsConstructor;
import org.roronoa.spring_security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    IUserService userService;
    private final JwtUtils jwtUtils;
    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,

            FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("authorizationApp");
        final String userEmail;
        final String jwtToken;
        if (authHeader == null || !authHeader.startsWith("oussam")){
            filterChain.doFilter(request,response);
        } else {
            jwtToken=authHeader.substring(7);
            userEmail = jwtUtils.extractUsername(jwtToken);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userService.findByEmail(userEmail);

                if (jwtUtils.isTokenValid(jwtToken,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }
            filterChain.doFilter(request,response);
        }
    }
}
