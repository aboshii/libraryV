package com.library.springlibrary.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        http.formLogin(login -> login
                .loginPage("/login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .permitAll());
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                .permitAll());
        http.csrf().disable();
        return http.build();
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
