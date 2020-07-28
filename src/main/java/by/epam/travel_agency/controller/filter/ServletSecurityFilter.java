package by.epam.travel_agency.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Servlet security filter.
 */
@WebFilter(urlPatterns = {"/pages/*"}, initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class ServletSecurityFilter implements Filter {
    private String indexPath;
    private String INDEX_PATH="INDEX_PATH";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter(INDEX_PATH);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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