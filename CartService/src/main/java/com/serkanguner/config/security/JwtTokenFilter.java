package com.serkanguner.config.security;

import com.serkanguner.exception.CartServiceException;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;
    private final JwtUserDetails jwtUserDetails;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //requestte gelen bearer tokeni yakalama:
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null) {

            String token = bearerToken.substring(7);
            System.out.println("JwtTokenFilter ile yakalanan token:" + token);
            String adminId =
                    jwtTokenManager.getIdFromToken(token)
                            .orElseThrow(() -> new CartServiceException(ErrorType.INVALID_TOKEN));
            System.out.println("JwtTokenFilter ile yakalanan Id:" + adminId);


            UserDetails userDetails = jwtUserDetails.loadToken(token);



            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            null,
                            userDetails.getAuthorities());


            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            System.out.println(userDetails.getAuthorities());


        }

        filterChain.doFilter(request,
                response);

    }
}
