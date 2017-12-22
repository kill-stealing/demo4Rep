package com.citi.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class TestF
 */
@WebFilter("/*")
public class TestF implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		/*BufferedReader br=request.getReader();
		String temp="";
		String str="";
		while ((temp=br.readLine())!=null) {
			str+=temp;
		}
		
		System.out.println("str "+str);*/
		
		BodyReaderHttpServletRequestWrapper requestWrapper = 
				new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
		
		System.out.println("body "+requestWrapper.getBody());
		
        chain.doFilter(requestWrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
