package com.sdjr2.rest_contact_meanssb.config.auth;

import com.sdjr2.rest_contact_meanssb.config.auth.filters.JwtAuthenticationFilter;
import com.sdjr2.rest_contact_meanssb.config.auth.filters.JwtValidationFilter;
import com.sdjr2.sb.library_commons.models.enums.RoleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * {@link SpringSecurityConfig} class.
 * <p>
 * <strong>Config</strong> - Security configuration about application.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/08/12
 * @since 24/08/03
 */
@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

	private static final String[] AUTH_WHITE_LIST = {
			"/h2-console/**",
			"/api-docs/**",
			"/swagger-ui/**",
			"/swagger-resources/**",
			"/console/**"
	};

	@Autowired
	private AuthenticationConfiguration authConfig;

	@Bean
	AuthenticationManager authenticationManager () throws
																								 Exception {
		return this.authConfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain ( HttpSecurity http ) throws
																															 Exception {
		return http.authorizeHttpRequests( auth -> auth
						// Generic
						.requestMatchers( SpringSecurityConfig.AUTH_WHITE_LIST ).permitAll()
						// Users
						.requestMatchers( HttpMethod.GET, "/users" ).permitAll()
						.requestMatchers( HttpMethod.GET, "/users/**" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple() )
						.requestMatchers( HttpMethod.POST, "/users" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple(),
								RoleTypeEnum.ROLE_USER.getValueSimple() )
						.requestMatchers( HttpMethod.PUT, "/users/{userId}" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple() )
						.requestMatchers( HttpMethod.DELETE, "/users/{userId}" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple() )
						// Roles
						.requestMatchers( "/roles" ).hasRole( RoleTypeEnum.ROLE_ADMIN.getValueSimple() )
						.requestMatchers( "/roles/**" ).hasRole( RoleTypeEnum.ROLE_ADMIN.getValueSimple() )
						// Addresses
						.requestMatchers( "/addresses" ).permitAll()
						.requestMatchers( HttpMethod.GET, "/addresses/**" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple() )
						.requestMatchers( HttpMethod.POST, "/addresses" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple(),
								RoleTypeEnum.ROLE_USER.getValueSimple() )
						.requestMatchers( HttpMethod.PUT, "/addresses/{addressId}" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple() )
						.requestMatchers( HttpMethod.DELETE, "/addresses/{addressId}" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple() )
						// Contacts
						.requestMatchers( "/contacts" ).permitAll()
						.requestMatchers( HttpMethod.GET, "/contacts/**" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple() )
						.requestMatchers( HttpMethod.POST, "/contacts" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple(),
								RoleTypeEnum.ROLE_USER.getValueSimple() )
						.requestMatchers( HttpMethod.PUT, "/contacts/{contactId}" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple(), RoleTypeEnum.ROLE_MEMBER.getValueSimple() )
						.requestMatchers( HttpMethod.DELETE, "/contacts/{contactId}" ).hasAnyRole(
								RoleTypeEnum.ROLE_ADMIN.getValueSimple() )
						.anyRequest().authenticated()
				)
				.addFilter( new JwtAuthenticationFilter( this.authenticationManager() ) )
				.addFilter( new JwtValidationFilter( this.authenticationManager() ) )
				// because this is an ApiRestful without view level.
				.csrf( csrf -> csrf
						.ignoringRequestMatchers( SpringSecurityConfig.AUTH_WHITE_LIST )
						.disable() )
				// when it is consumed by frontends
				.cors( cors -> cors.configurationSource( this.corsConfigurationSource() ) )
				// because the session (user data) is sent in the token, so, https request is stateless.
				.sessionManagement( manag -> manag.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
				.headers( headers -> headers.frameOptions( HeadersConfigurer.FrameOptionsConfig::disable ) )
				.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource () {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOriginPatterns( List.of( "*" ) );
		config.setAllowedMethods(
				List.of( HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name() ) );
		config.setAllowedHeaders( List.of( HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE ) );
		config.setAllowCredentials( true );

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration( "/**", config );

		return source;
	}

	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter () {
		FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
				new CorsFilter( this.corsConfigurationSource() ) );
		corsBean.setOrder( Ordered.HIGHEST_PRECEDENCE );

		return corsBean;
	}
}
