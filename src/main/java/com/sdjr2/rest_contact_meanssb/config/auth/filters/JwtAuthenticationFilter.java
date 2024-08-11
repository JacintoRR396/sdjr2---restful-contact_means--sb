package com.sdjr2.rest_contact_meanssb.config.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdjr2.rest_contact_meanssb.config.auth.TokenJwtConfig;
import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserAuthReqDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserAuthRespDTO;
import com.sdjr2.sb.library_commons.exceptions.AppExceptionCodeEnum;
import com.sdjr2.sb.library_commons.exceptions.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * {@link JwtAuthenticationFilter} class.
 * <p>
 * <strong>Config</strong> - Jwt Auth Filter about application.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/08/11
 * @since 24/08/03
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authManager;

	public JwtAuthenticationFilter ( AuthenticationManager authenticationManager ) {
		this.authManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication ( HttpServletRequest request, HttpServletResponse response ) throws
																																																					 AuthenticationException {
		UserAuthReqDTO userAuthReqDTO = null;
		try {
			userAuthReqDTO = new ObjectMapper().readValue( request.getInputStream(), UserAuthReqDTO.class );
		} catch ( IOException ex ) {
			throw new CustomException( ex, AppExceptionCodeEnum.STATUS_50000 );
		}

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				userAuthReqDTO.getUsername(), userAuthReqDTO.getPassword() );

		return this.authManager.authenticate( authToken );
	}

	@Override
	protected void successfulAuthentication ( HttpServletRequest request, HttpServletResponse response, FilterChain chain,
																						Authentication authResult ) throws
																																				IOException {
		String username = ( ( User ) authResult.getPrincipal() ).getUsername();

		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		Claims claims = Jwts.claims()
				//.add( TokenJwtConfig.CLAIM_AUTHORITIES, roles )
				.add( TokenJwtConfig.CLAIM_AUTHORITIES, new ObjectMapper().writeValueAsString( roles ) )
				.build();

		String tokenJWT = Jwts.builder()
				.subject( username )
				.signWith( TokenJwtConfig.SECRET_KEY )
				.claims( claims )
				.issuedAt( new Date() )
				.expiration( new Date( System.currentTimeMillis() + ( 2 * 60 * 60 * 1000 ) ) )
				.compact();

		UserAuthRespDTO userAuthRespDTO = UserAuthRespDTO.builder()
				.username( username )
				.token( tokenJWT )
				.message( String.format( "Hello %s, you have logged in successfully.", username ) )
				.build();

		response.addHeader( HttpHeaders.AUTHORIZATION, TokenJwtConfig.PREFIX_TOKEN + tokenJWT );
		response.setContentType( MediaType.APPLICATION_JSON_VALUE );
		response.getWriter().write( new ObjectMapper().writeValueAsString( userAuthRespDTO ) );
		response.setStatus( HttpStatus.OK.value() );
	}

	@Override
	protected void unsuccessfulAuthentication ( HttpServletRequest request, HttpServletResponse response, AuthenticationException failed ) throws
																																																																				 IOException {
		String message = "Authentication error, incorrect username and / or password.";
		TokenJwtConfig.createResponseKo( response, failed, message );
	}
}
