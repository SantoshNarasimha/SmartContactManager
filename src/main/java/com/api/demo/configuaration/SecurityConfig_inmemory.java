package com.api.demo.configuaration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



@Configuration															
@EnableWebSecurity
public class SecurityConfig_inmemory {
	
	
	
	
	
	
	/*
	@Bean
	public UserDetailsService userdetailservice() {
		
		UserDetails userdetailsone = User.withUsername("User1").password(passwordencoder().encode("Pass1")).roles("User").build();
		
		UserDetails userdetailtwo = User.withUsername("User2").password(passwordencoder().encode("Pass2")).roles("User").build();
		
		UserDetails admin = User.withUsername("Admin").password(passwordencoder().encode("PassAdmin")).roles("Admin").build();
		
		return new InMemoryUserDetailsManager(userdetailsone, userdetailtwo, admin);
	}
	
	
	
	
	@Bean
	public PasswordEncoder passwordencoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	
	@Bean
	
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf(csrfCustomizer -> csrfCustomizer.disable());
		httpSecurity.authorizeHttpRequests(request ->
	    request
	        .requestMatchers(
	            "/contact/home",
	            "/favicon.ico",
	            "/css/**",
	            "/js/**",
	            "/images/**"
	        ).permitAll()
	        .anyRequest().authenticated()
	);

		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.sessionManagement(session ->
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
				
		return httpSecurity.build();
	}
	
	
	*/

}