package com.cagyj.books.filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter(value = {"/management/index.html", "/management/book/*", "/management/comments/*"})
public class ManagementFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1. trans to be related with the HTTP protocol
        var req = (HttpServletRequest) servletRequest;
        var resp = (HttpServletResponse) servletResponse;

        // 2. get the data from session
        Object username = req.getSession().getAttribute("loginAdmin");

        // 3. determine the username
        if (username == null || username.equals("")) {
            // 4. redirect to login page
            resp.sendRedirect(req.getContextPath() + "/management/");
            return;
        }

        // 4. continue
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
