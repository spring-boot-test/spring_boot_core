package com.hubert.core.exception;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
	
	private static final String CONTEXT_ATTRIBUTE = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		ServletContext servletContext = request.getServletContext();
        
        String message = "Internal Server Error";
        int statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        
        WebApplicationContext context = (WebApplicationContext) servletContext
                .getAttribute(CONTEXT_ATTRIBUTE);
        if (ex instanceof BaseException) {
            BaseException baseException = (BaseException) ex;
            String code = baseException.getCode();
            Object[] args = baseException.getValues();
            message = context.getMessage(code, args, ex.getMessage(),
                    request.getLocale());
            baseException.setMessage(message);
        } else {
            statusCode = HttpServletResponse.SC_NOT_FOUND;
            message = "Request Not Found";
        }
        LOGGER.error(message, ex);
        try {
            response.sendError(statusCode);
            response.getWriter().flush();
        } catch (IOException e) {
            LOGGER.error("设置错误码失败！", e);
        }
        
		return null;
	}

}
