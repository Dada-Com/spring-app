package com.dada.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User.builder()
	                .username("dada")
	                .password(passwordEncoder().encode("dada"))
	                .roles("ADMIN")
	                .build();
	        UserDetails user1 = User.builder()
	                .username("dada1")
	                .password(passwordEncoder().encode("dada1"))
	                .roles("ADMIN")
	                .build();
	        return new InMemoryUserDetailsManager(user, user1);
	    }
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(crsf -> crsf.disable())
				.cors(cors -> cors.disable())
				.authorizeHttpRequests(auth->{auth
					.requestMatchers("/api/**").permitAll();								
				})
				.formLogin(Customizer.withDefaults())
				//.oauth2Login(Customizer.withDefaults())
			    .build();
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  
    @Bean
    public AuthenticationManager  authenticationManager(AuthenticationConfiguration builder) throws Exception {
    	return builder.getAuthenticationManager();
    }
    
    
}

