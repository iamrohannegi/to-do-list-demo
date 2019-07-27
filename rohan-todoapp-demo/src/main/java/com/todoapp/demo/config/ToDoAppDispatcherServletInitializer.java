package com.todoapp.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//To register dispatcher servlet and use Java-based Spring configuration
public class ToDoAppDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ToDoAppConfig.class};
	}

	//Specify servlet mapping for dispatcher servlet
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
