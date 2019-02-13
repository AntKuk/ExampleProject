package com.exampleproject.web.rest;

import com.exampleproject.web.rest.config.HibernateConfig;
import com.exampleproject.web.rest.config.SpringConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
/*
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { EntryPoint.class};
    }

*/
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SpringConfig.class, HibernateConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/backend/*"};
    }

    @Override
    protected String getServletName() {
        return "webservlet";
    }
}
