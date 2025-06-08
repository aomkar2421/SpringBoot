package com.om.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(request->request.requestMatchers("login", "register").permitAll().anyRequest().authenticated());
		http.httpBasic(Customizer.withDefaults())
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//		http.csrf(csrf->csrf.disable())
//		.authorizeHttpRequests(authorize->authorize
//				.requestMatchers("/", "/signin", "/register", "/saveUser", "/login").permitAll()
//				.requestMatchers("/user/profile", "/user/home").authenticated())
//		.formLogin(form->form
//				.loginPage("/signin").loginProcessingUrl("/loginUser").defaultSuccessUrl("/user/profile").permitAll());

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
