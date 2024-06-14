package com.sdjr2.rest_contact_meanssb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * {@link MvcConfig} class.
 * <p>
 * <strong>Config</strong> - Global configuration about mvc.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/06/14
 * @since 24/06/14
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Autowired
  @Qualifier("loadingTimeInterceptor")
  private HandlerInterceptor loadingTimeInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loadingTimeInterceptor);
  }
}
