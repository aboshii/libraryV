package com.library.springlibrary.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/").permitAll()
                .requestMatchers("/styles/**").permitAll()
                .requestMatchers("/pictures/**").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/confirmation").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin(login -> login
                .loginPage("/login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .successForwardUrl("/library")
                .permitAll());
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                .permitAll());
        http.csrf().disable();
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

/* //instead of CustimInMemoryUserDetailsManager class
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder userBuilder = User.builder();
        UserDetails admin = userBuilder.username("1").password("{noop}1").roles("ADMIN").build();
        UserDetails user1 = userBuilder.username("john").password("{noop}asdf1234").roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user1);
    }*/
}
