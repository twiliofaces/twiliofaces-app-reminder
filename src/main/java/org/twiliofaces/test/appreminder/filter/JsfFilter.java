package org.twiliofaces.test.appreminder.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "JsfFilter", urlPatterns = "*.jsf")
public class JsfFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		Wrapper responseWrapper = new Wrapper((HttpServletResponse) response);
		chain.doFilter(request, responseWrapper);
		// I just want the output on stdout at the moment...
		System.out.println(responseWrapper.toString());
		PrintWriter out = response.getWriter();
		out.write(responseWrapper.toString());
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
