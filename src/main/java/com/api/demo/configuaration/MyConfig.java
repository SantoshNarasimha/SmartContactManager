package com.api.demo.configuaration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/*

On login,Spring Security intercepts the request,validates credentials using 
UserDetailsService and PasswordEncoder,stores authentication in SecurityContext,and then 
allows access.

*/



/*
🧠 Remember this (VERY IMPORTANT)

UserDetails = Spring Security user

DaoAuthenticationProvider = validator

AuthenticationManager = coordinator

SecurityContext = logged-in user memory 	
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MyConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll())
				.formLogin(form -> form.loginPage("/contact/signin").defaultSuccessUrl("/user/home", true).permitAll()).logout(logout -> logout.permitAll());

		return http.build();
	}
}
