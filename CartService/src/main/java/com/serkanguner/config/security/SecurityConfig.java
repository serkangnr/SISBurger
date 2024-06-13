package com.serkanguner.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("options/findall/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/options/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/options").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/menus/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/menus").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/menuitem/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/menuitem").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/component/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/component").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/categories/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/categories/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/order/urunsec/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/order/urunlistele/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/order/menuslist/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/order/categorieslist/**").hasAuthority("USER")

                        //.requestMatchers("/api/v1/order/**").hasAnyAuthority("USER","ADMIN")
                        /*.requestMatchers("/api/v1/auth/softdelete").hasAnyAuthority("ADMIN")*/


                        .anyRequest().authenticated())
                .addFilterBefore(jwtTokenFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());
				/*.cors(httpSecurityCorsConfigurer ->
						      httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource));
*/
        return httpSecurity.build();

    }
}
