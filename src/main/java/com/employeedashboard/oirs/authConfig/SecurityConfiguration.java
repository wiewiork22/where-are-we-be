package com.employeedashboard.oirs.authConfig;

import java.util.Arrays;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> cors.configurationSource(request -> {
			CorsConfiguration corsConfiguration = new CorsConfiguration();
			corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
			corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
			corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
			return corsConfiguration;
		})).csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/auth/**").permitAll().requestMatchers("/employees").permitAll()// later
																														// only
																														// for
																														// admin
								.anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(LogoutConfigurer::permitAll);
		return http.build();
	}
}