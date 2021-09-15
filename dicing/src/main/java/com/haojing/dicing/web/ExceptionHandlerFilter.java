package com.haojing.dicing.web;

import com.haojing.dicing.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter implements Filter {
	private static final String ERR_MSG = "{\"code\":%d, \"msg\":\"%s\"}";
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			Throwable t = e;
			response.setContentType("application/json;charset=UTF-8");
			while (t != null) {
				if (t instanceof ServiceException) {
					ServiceException se = (ServiceException) t;
					((HttpServletResponse) response).setStatus(400);
					response.getWriter().write(String.format(ERR_MSG, se.getCode(), se.getMessage()));
					response.getWriter().flush();
					return;
				}
				t = t.getCause();
			}
			logger.error(e.getMessage(), e);
			((HttpServletResponse) response).setStatus(500);
			response.getWriter().write(String.format(ERR_MSG, -1, e.getMessage()));
			response.getWriter().flush();
			return;
		}
	}

	@Override
	public void destroy() {

	}

}
