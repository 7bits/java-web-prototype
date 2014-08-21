package it.sevenbits.project.application.config.util;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.Log4jConfigurer;

import javax.inject.Inject;

/**
 * Log4J Configuration
 */
@Configuration
@PropertySource("file:${project.config}/configurations/application/server.properties")
public class Log4JConfig {

    @Inject
    private Environment environment;

    /**
     * Setup Log4j logger
     */
    @Bean(name = "log4jInitialization")
    public MethodInvokingFactoryBean log4jInitialization() {
        System.setProperty("server.log.path", environment.getProperty("server.log.path"));
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(Log4jConfigurer.class);
        methodInvokingFactoryBean.setTargetMethod("initLogging");
        methodInvokingFactoryBean.setArguments(new Object[]{"file:${project.config}/configurations/application/log4j.xml"});

        return methodInvokingFactoryBean;
    }
}