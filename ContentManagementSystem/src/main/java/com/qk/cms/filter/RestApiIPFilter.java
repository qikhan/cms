package com.qk.cms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class RestApiIPFilter implements Filter {

	private FilterConfig config;
	// the regex must define whole string to match - for example a substring
	// without .* will not match
	// note the double backslashes that need to be present in Java code but not
	// in web.xml
	private String IP_REGEX = "127\\.0\\.0\\.\\d+";

	// private String IP_REGEX = "172\\.20\\..*";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		// optionally you can get regex from init parameter overwriting the
		// class' private variable
		IP_REGEX = config.getInitParameter("IP_REGEX");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String ip = request.getRemoteAddr();
		HttpServletResponse httpResp = null;
		if (response instanceof HttpServletResponse)
			httpResp = (HttpServletResponse) response;
		if (ip.matches(IP_REGEX)) {
			chain.doFilter(request, response);
		} else {
			httpResp.sendError(HttpServletResponse.SC_FORBIDDEN,
					"Your own message 403 Forbidden");
		}
	}

	@Override
	public void destroy() {
	}

}