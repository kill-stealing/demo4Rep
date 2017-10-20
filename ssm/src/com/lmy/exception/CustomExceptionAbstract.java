package com.lmy.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public class CustomExceptionAbstract extends AbstractHandlerExceptionResolver{

	@Override
	protected ModelAndView doResolveException(
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			Exception exception) {
		// TODO Auto-generated method stub
		return null;
	}

}
