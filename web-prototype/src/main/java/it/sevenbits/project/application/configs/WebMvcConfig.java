package it.sevenbits.project.application.configs;

import it.sevenbits.project.application.web.utils.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * Spring Web Configuration
 */
@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Configure Interceptors
     * @param registry    Interceptors Registry
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        if (this.applicationContext.containsBean("handlerInterceptor")) {
            registry.addInterceptor((HandlerInterceptor) applicationContext.getBean("handlerInterceptor"));
        }
    }

    /**
     * Configure Request Mapping Handler
     * @return Request Mapping Handler
     */
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
        if (this.applicationContext.containsBean("messageConverter")) {
            requestMappingHandlerAdapter.setMessageConverters(
                    (List<HttpMessageConverter<?>>) applicationContext.getBean("messageConverter")
            );
        }

        return requestMappingHandlerAdapter;
    }

    /**
     * Configure content negotiation rules
     */
    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}

