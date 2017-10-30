package com.lmy.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lmy.controller.ItemsController;

public class GetApplicationContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		GetApplicationContext.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void main(String[] args) {
		ItemsController test = GetApplicationContext
				.getApplicationContext().getBean(ItemsController.class);

	}

}
