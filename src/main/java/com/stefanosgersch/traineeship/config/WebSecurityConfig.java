package com.stefanosgersch.traineeship.config;

import com.stefanosgersch.traineeship.domain.Role;
import com.stefanosgersch.traineeship.repository.UserRepository;
import com.stefanosgersch.traineeship.security.CustomSecuritySuccessHandler;
import com.stefanosgersch.traineeship.service.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;


@Configuration
public class WebSecurityConfig {

    private final UserRepository userRepository;
    private final CustomSecuritySuccessHandler customSecuritySuccessHandler;

    public WebSecurityConfig(UserRepository userRepository,
                             CustomSecuritySuccessHandler customSecuritySuccessHandler) {
        this.userRepository = userRepository;
        this.customSecuritySuccessHandler = customSecuritySuccessHandler;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        /*
         * Configures the custom authentication provider with
         * the custom user details service and encoder
         */
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // add a security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*
         * set up the filter chain, last we have the
         * authentication filter for all requests
         */
        http
                .authorizeHttpRequests(
                (auth) -> auth
                        .requestMatchers("/", "/login", "/register", "/save").permitAll()
                        .requestMatchers("/student/**").hasAnyAuthority("STUDENT") // ??? ZAS is this needed ??? - changed from account to user
                        .requestMatchers("/company/**").hasAnyAuthority("COMPANY")
                        .requestMatchers("/committee/**").hasAnyAuthority("COMMITTEE_MEMBER")
                        .requestMatchers("/professor/**").hasAnyAuthority("PROFESSOR")
                        .anyRequest().authenticated()
                )

                .formLogin(
                    (form) -> form
                        .loginPage("/login")
                        .failureUrl("/login?error=true")
                        .successHandler(customSecuritySuccessHandler)
                        .usernameParameter("username")
                        .passwordParameter("password")
                )
                .logout(
                    (logout) -> logout
                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                )
                        .authenticationProvider(authenticationProvider());

        return http.build();
    }
}
