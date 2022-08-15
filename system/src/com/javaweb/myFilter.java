package com.javaweb;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class myFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain Chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String servletPath = request.getServletPath();
        HttpSession session = request.getSession(false);
        if ("/user/login".equals(servletPath) || "/welcome".equals(servletPath) || "/user/register".equals(servletPath) ||
                "/login.jsp".equals(servletPath) || "/register.jsp".equals(servletPath) ||
                (session != null && session.getAttribute("User") != null)){
            Chain.doFilter(request,response);
        }else{
            response.sendRedirect("/system/welcome");
        }
    }
}
