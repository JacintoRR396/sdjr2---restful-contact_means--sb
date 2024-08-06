package com.sdjr2.rest_contact_meanssb.config.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdjr2.rest_contact_meanssb.config.auth.TokenJwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * {@link JwtValidationFilter} class.
 * <p>
 * <strong>Config</strong> - Jwt Validation Filter about application.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/08/04
 * @since 24/08/04
 */
public class JwtValidationFilter extends BasicAuthenticationFilter {

	public JwtValidationFilter ( AuthenticationManager authenticationManager ) {
		super( authenticationManager );
	}

	@Override
	protected void doFilterInternal ( HttpServletRequest request, HttpServletResponse response, FilterChain chain ) throws
																																																									IOException,
																																																									ServletException {
		String headerBearer = request.getHeader( HttpHeaders.AUTHORIZATION );
		if ( Objects.isNull( headerBearer ) || !headerBearer.startsWith( TokenJwtConfig.PREFIX_TOKEN ) ) {
			chain.doFilter( request, response );
			return;
		}
		String tokenJWT = headerBearer.replace( TokenJwtConfig.PREFIX_TOKEN, StringUtils.EMPTY );

		try {
			Claims claims = Jwts.parser().verifyWith( TokenJwtConfig.SECRET_KEY ).build()
					.parseSignedClaims( tokenJWT )
					.getPayload();
			String username = claims.getSubject();
			Object authorities = claims.get( TokenJwtConfig.CLAIM_AUTHORITIES );
			Collection<? extends GrantedAuthority> roles = Arrays.asList( new ObjectMapper()
					// Adapt the authorities attribute to roles in the json
					.addMixIn( SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class )
					.readValue( authorities.toString().getBytes(), SimpleGrantedAuthority[].class ) );

			// The password is only validated when the token is created
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					username, null, roles );
			SecurityContextHolder.getContext().setAuthentication( authToken );
			chain.doFilter( request, response );
		} catch ( JwtException ex ) {
			String message = "The JWT token is invalid.";
			TokenJwtConfig.createResponseKo( response, ex, message );
		}
	}
}
