package main.java.by.epam.travel_agency.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/pages/*" }, initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class ServletSecurityFilter implements Filter {
	private String indexPath;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		indexPath = filterConfig.getInitParameter("INDEX_PATH");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//transfer to pointed page
		httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}