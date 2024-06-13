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
                        .requestMatchers("options/findall/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/cart/update/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/cart/update").hasAuthority("USER")
                        .requestMatchers("/api/v1/cart/confirmation/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/cart/confirmation").hasAuthority("USER")
                        .requestMatchers("/api/v1/cart/findall/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/cart/findall").hasAuthority("USER")
                        .requestMatchers("/api/v1/cart/delete/**").hasAuthority("USER")
                        .requestMatchers("/api/v1/cart/delete").hasAuthority("USER")


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
