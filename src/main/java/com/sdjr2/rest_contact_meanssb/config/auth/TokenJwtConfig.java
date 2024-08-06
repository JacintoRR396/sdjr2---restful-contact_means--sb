package com.sdjr2.rest_contact_meanssb.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserAuthRespDTO;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.crypto.SecretKey;
import java.io.IOException;

/**
 * {@link TokenJwtConfig} class.
 * <p>
 * <strong>Config</strong> - Token JWT configuration about application.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/08/04
 * @since 24/08/04
 */
public class TokenJwtConfig {

	public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
	public static final String PREFIX_TOKEN = "Bearer ";
	public static final String CLAIM_AUTHORITIES = "authorities";

	private TokenJwtConfig () {
		throw new IllegalStateException( "Utility class" );
	}

	public static void createResponseKo ( HttpServletResponse response, Exception ex, String message ) throws
																																																		 IOException {
		UserAuthRespDTO userAuthRespDTO = UserAuthRespDTO.builder()
				.error( ex.getMessage() )
				.message( message )
				.build();

		response.setContentType( MediaType.APPLICATION_JSON_VALUE );
		response.getWriter().write( new ObjectMapper().writeValueAsString( userAuthRespDTO ) );
		response.setStatus( HttpStatus.UNAUTHORIZED.value() );
	}
}
