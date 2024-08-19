package com.sdjr2.rest_contact_meanssb.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * {@link OpenApiProperties} class.
 * <p>
 * <strong>Config - Properties</strong> - Open API properties.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/08/19
 * @since 24/08/19
 */
@Configuration
@ConfigurationProperties(prefix = "openapi")
@Data
public class OpenApiProperties {

	// Open API Data
	private String version;
	private String title;
	private String description;
	private String termsOfService;
	private Map<String, String> license;		// name, url

	// Personal Data
	private Map<String, String> author;		// name, email, url

	// Micro Data
	private Map<String, String> micro;		// urlDev, urlPre, urlProd
}
