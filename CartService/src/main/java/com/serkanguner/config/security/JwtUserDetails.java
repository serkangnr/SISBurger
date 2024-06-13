package com.serkanguner.config.security;


import com.serkanguner.dto.request.TokenReturnDto;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.exception.ProductServiceException;
import com.serkanguner.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {
    private final JwtTokenManager jwtTokenManager;
    private final CategoryService categoryService;
    private final ComponentService componentService;
    private final MenuItemService menuItemService;
    private final MenuService menuService;
    private final OptionService optionService;
    private final OrderService orderService;

    public TokenReturnDto getToken(String token){
        String id = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_TOKEN));
        String name =jwtTokenManager.getNameFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_UNAUTHORIZED));
        //String password =jwtTokenManager.getPasswordFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_UNAUTHORIZED));
        String role = jwtTokenManager.getRoleFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_UNAUTHORIZED));

        TokenReturnDto dto = TokenReturnDto.builder()
                .id(id)
                .name(name)
                .role(role)
                .build();

        return dto;
    }
    public TokenReturnDto getUserToken(String token){
        String id = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_TOKEN));
        String name =jwtTokenManager.getNameFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_UNAUTHORIZED));
        String role = jwtTokenManager.getRoleFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_UNAUTHORIZED));

        TokenReturnDto dto = TokenReturnDto.builder()
                .id(id)
                .name(name)
                .password("")
                .role(role)
                .build();

        return dto;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadToken(String token){
        TokenReturnDto dto = getToken(token);
        if (dto.getRole().equals("ADMIN")) {
            return loadAdminByToken(token);
        }else {
            return loadUserByToken(token);
        }
    }

    public UserDetails loadAdminByToken(String token) {
        TokenReturnDto dto = getToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>();


            authorities.add(new SimpleGrantedAuthority(dto.getRole()));



        return User.builder()
                .username(dto.getName())
                .password("")
                .authorities(authorities)
                .build();
    }
    public UserDetails loadUserByToken(String token) {
        TokenReturnDto userToken = getUserToken(token);
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(userToken.getRole()));
        return User.builder()
                .username(userToken.getName())
                .password("")
                .authorities(authorities)
                .build();

    }
}
