package com.sdjr2.rest_contact_meanssb.config.auth.filters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {@link SimpleGrantedAuthorityJsonCreator} class.
 * <p>
 * <strong>Config</strong> - Simple Granted Authority Json Creator about authorities in claims.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/08/04
 * @since 24/08/04
 */
public abstract class SimpleGrantedAuthorityJsonCreator {

	@JsonCreator
	public SimpleGrantedAuthorityJsonCreator ( @JsonProperty("authority") String role ) {
	}
}
