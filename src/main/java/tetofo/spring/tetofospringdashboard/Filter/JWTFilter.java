package tetofo.spring.tetofospringdashboard.Filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tetofo.spring.tetofospringdashboard.Service.Exception.ServiceException;
import tetofo.spring.tetofospringdashboard.Service.Impl.JWTService;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    private static final Logger LOG = LoggerFactory.getLogger("JWTFilter");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = header.substring(7);
        final String username;
        try {
            username = jwtService.getUsername(jwt);
        } catch (ServiceException e) {
            LOG.error("JWT is missing subject.", e);
            filterChain.doFilter(request, response);
            return;
        }
        final UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            LOG.error("User %s does not exist.".formatted(username), e);
            filterChain.doFilter(request, response);
            return;
        }
        try {
            if (!jwtService.isJWTValid(jwt, userDetails)) {
                filterChain.doFilter(request, response);
                return;

            } 
        } catch (ServiceException e) {
            LOG.error("JWT is not valid", e);
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                    Collections.emptyList() : userDetails.getAuthorities()
            );
        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    
}
