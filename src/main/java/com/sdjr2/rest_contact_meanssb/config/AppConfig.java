package com.sdjr2.rest_contact_meanssb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * {@link AppConfig} class.
 * <p>
 * <strong>Config</strong> - Global configuration about application.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/06/13
 * @since 24/06/13
 */
@Configuration
@PropertySources({
  @PropertySource("classpath:/properties/config.properties"),
  @PropertySource("classpath:/properties/messages.properties")
})
public class AppConfig {
}
