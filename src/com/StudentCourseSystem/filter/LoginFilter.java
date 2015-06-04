package com.StudentCourseSystem.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		Object user = req.getSession().getAttribute("currentUser");
		if(user==null){
			if (uri.endsWith("StudentCourseSystem/") 
					||uri.contains("/StudentCourseSystem/frontPage/")
					||uri.endsWith("gif")||uri.endsWith("js")
					||uri.endsWith("css")||uri.endsWith("png")
					|| uri.endsWith("StudentCourseSystem/login.jsp")) {
				chain.doFilter(request, response);
				return;
			}else if(!uri.endsWith(".action")){
				res.sendRedirect("../login.jsp");
				return;
			}else{
				chain.doFilter(request, response);
				return;
			}
		}else{
			chain.doFilter(request, response);
			return;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
