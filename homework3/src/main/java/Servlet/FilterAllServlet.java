package Servlet;

import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterAllServlet implements Filter {
    private List<Class<? extends AbstractRoutableServlet>> servletClasses;
    private ServletRouter servletRouter;
    private static Map<String, HttpServlet> urls = new HashMap<>();

    public FilterAllServlet(ServletRouter servletRouter) {
        this.servletRouter = servletRouter;
        urls = servletRouter.getUrls();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        String url = request1.getRequestURI();
        if(!urls.containsKey(url)) {
            response1.sendRedirect("/user");
        }
        else {
            urls.get(url).service(request,response);
        }
    }

    @Override
    public void destroy() {
    }
}
