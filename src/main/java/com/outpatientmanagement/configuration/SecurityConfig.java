package com.outpatientmanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.outpatientmanagement.security.JwtAuthenticationFilter;
import com.outpatientmanagement.security.JwtAuthorizationFilter;
import com.outpatientmanagement.security.JwtUtil;

@Configuration
public class SecurityConfig {

	private final JwtUtil jwtUtil;

	public SecurityConfig(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService,
			AuthenticationManager authenticationManager) throws Exception{
		
		JwtAuthenticationFilter jwtAuthFilter=new JwtAuthenticationFilter(authenticationManager, jwtUtil);
		
		jwtAuthFilter.setFilterProcessesUrl("/auth/login");
		
		http
			.csrf(csrf->csrf.disable())
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth->auth 
			.requestMatchers("/auth/login", "/auth/register", "/auth/forgot-password",
					"/auth/reset-password","/auth/logout").permitAll()
			.requestMatchers("/api/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR")
			.requestMatchers("/api/admin/**").hasRole("ADMIN")
			.requestMatchers("/api/doctors/**").hasRole("DOCTOR")
			.requestMatchers("/api/patients/**").hasRole("PATIENT")
			.anyRequest().authenticated()
			)
			.addFilterBefore(new JwtAuthorizationFilter(jwtUtil, userDetailsService),
				    UsernamePasswordAuthenticationFilter.class)
			.formLogin(form->form.disable())
			.logout(logout->logout.permitAll());
		
		return http.build();
	}
}
