package com.sdjr2.rest_contact_meanssb.config.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @upgrade 24/06/13
 * @since 24/06/13
 */
@Component
public class LoadingTimeInterceptor implements HandlerInterceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    HandlerMethod controller = ((HandlerMethod) handler);
    LOGGER.info(controller.getBean().getClass().getSimpleName() + " : " + controller.getMethod().getName() +
      " » coming into ... ");

    long startTime = System.currentTimeMillis();
    request.setAttribute("startTime", startTime);

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) {
    long startTime = (Long) request.getAttribute("startTime");
    long endTime = System.currentTimeMillis();
    long timeElapsed = endTime - startTime;
    LOGGER.info("Time elapsed: " + timeElapsed + " miliseconds !!!");

    HandlerMethod controller = ((HandlerMethod) handler);
    LOGGER.info(
      controller.getBean().getClass().getSimpleName() + " : " + controller.getMethod().getName() + " » going out ... ");
  }
}
