/*
package com.library.springlibrary.configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
class CustomInMemoryUserDetailsManager extends InMemoryUserDetailsManager {

    public CustomInMemoryUserDetailsManager() {
        User.UserBuilder userBuilder = User.builder();
        UserDetails admin = userBuilder
                .username("admin")
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode("ssap"))
                .roles("ADMIN").build();
        UserDetails user1 = userBuilder
                .username("aboshii")
                .password("{MD5}" + new MessageDigestPasswordEncoder("MD5").encode("1111"))
                .roles("USER").build();
        UserDetails user2 = userBuilder
                .username("gorachora")
                .password("{argon2}" + Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8().encode("111"))
                .roles("USER").build();
        createUser(admin);
        createUser(user1);
        createUser(user2);
    }
}
*/
