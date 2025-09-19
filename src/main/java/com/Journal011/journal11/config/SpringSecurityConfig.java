package com.Journal011.journal11.config;

import com.Journal011.journal11.service.UserDetailsServiceimpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



    @Configuration
    public class SpringSecurityConfig {

        private final UserDetailsServiceimpl userDetailsServiceimpl; //userData

        public SpringSecurityConfig(UserDetailsServiceimpl userDetailsServiceimpl) { // xyz = new xyz()
            this.userDetailsServiceimpl = userDetailsServiceimpl;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(auth -> auth
                            .antMatchers("/journal/**","/user/**").authenticated() // username pwd...
                            .antMatchers("/admin/**").hasRole("ADMIN") // username pwd...
                            .anyRequest().permitAll()
                    )
                    .httpBasic()
                    .and()
                    .csrf().disable(); // Disable CSRF for stateless APIs

            return http.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
            return authConfig.getAuthenticationManager();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return userDetailsServiceimpl;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(userDetailsServiceimpl);
            provider.setPasswordEncoder(passwordEncoder());
            return provider;
        }
    }

