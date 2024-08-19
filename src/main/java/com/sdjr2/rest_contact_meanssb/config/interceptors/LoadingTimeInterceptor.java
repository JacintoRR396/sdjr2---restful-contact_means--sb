package com.sdjr2.rest_contact_meanssb.config.interceptors;

import com.sdjr2.rest_contact_meanssb.controllers.ContactController;
import com.sdjr2.sb.library_commons.config.BaseHandlerLogger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * {@link LoadingTimeInterceptor} class.
 * <p>
 * <strong>Config</strong> - Interceptor about loading time in controllers.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/08/17
 * @since 24/06/13
 */
@Component
public class LoadingTimeInterceptor implements HandlerInterceptor {

	BaseHandlerLogger logger = new BaseHandlerLogger( ContactController.class );

	@Override
	public boolean preHandle ( HttpServletRequest request, HttpServletResponse response, Object handler ) {
		if ( !( handler instanceof HandlerMethod ) ) {
			return true;
		}

		HandlerMethod controller = ( ( HandlerMethod ) handler );
		this.logger.info( request.getMethod() + " " + request.getRequestURI() );
		this.logger.info( controller.getBean().getClass().getSimpleName(), controller.getMethod().getName(),
				"coming into ... " );

		long startTime = System.currentTimeMillis();
		request.setAttribute( "startTime", startTime );

		return true;
	}

	@Override
	public void postHandle ( HttpServletRequest request, HttpServletResponse response, Object handler,
													 ModelAndView modelAndView ) {
		if ( !( handler instanceof HandlerMethod ) ) {
			return;
		}

		long startTime = ( Long ) request.getAttribute( "startTime" );
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;

		HandlerMethod controller = ( ( HandlerMethod ) handler );
		this.logger.info( controller.getBean().getClass().getSimpleName(), controller.getMethod().getName(),
				"going out in " + timeElapsed + "ms ... " );
	}
}
