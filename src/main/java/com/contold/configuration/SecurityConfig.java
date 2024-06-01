package com.contold.configuration;

import static com.contold.security.JWTConstants.LOGIN_URL;
import static com.contold.security.JWTConstants.SIGN_UP_URL;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.contold.security.JWTAuthenticationFilter;
import com.contold.security.JWTAuthorizationFilter;
import com.contold.security.JWTUtil;
import com.contold.service.UserDetailServiceImplementation;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserDetailServiceImplementation userService;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JWTUtil jwtUtil;

	@Autowired
	public SecurityConfig(UserDetailServiceImplementation userService, BCryptPasswordEncoder bCryptPasswordEncoder,
			JWTUtil jwtUtil) {

		this.userService = userService;

		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		AuthenticationManager authenticationManager = authenticationManager(
				httpSecurity.getSharedObject(AuthenticationConfiguration.class));

		httpSecurity.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
						.requestMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
						.requestMatchers(HttpMethod.POST, "/aa").permitAll()

//						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.anyRequest().authenticated())
				.addFilter(new JWTAuthenticationFilter(authenticationManager, jwtUtil, userService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager, jwtUtil))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return httpSecurity.build();
	}

	@Autowired
	void registerProvider(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*")); // 4200'a ayarla sonradan
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
//	    configuration.setAllowCredentials(true); // Gerekli olabilir

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
