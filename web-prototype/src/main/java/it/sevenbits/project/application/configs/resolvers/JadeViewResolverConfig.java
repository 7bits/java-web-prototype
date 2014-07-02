package it.sevenbits.project.application.configs.resolvers;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;
import it.sevenbits.project.application.web.utils.CustomJadeViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

/**
* Jade view resolver configuration
*/
@Configuration
public class JadeViewResolverConfig {

    @Value("#{systemProperties['project.config']}")
    private String propertyConfig;

    /**
     * Jade Template Loader setting
     * @return template loader
     */
    @Bean
    public SpringTemplateLoader templateLoader() {
        SpringTemplateLoader templateLoader = new SpringTemplateLoader();
        // TODO need research: doesn't work ${project.config} in next string. Reason - unknown
        templateLoader.setBasePath("file:" + propertyConfig + "/view-templates/application/");
        templateLoader.setEncoding("UTF-8");
        templateLoader.setSuffix(".jade");

        return templateLoader;
    }

    /**
     * Jade Configuration
     * @return jade configuration
     */
    @Bean
    public JadeConfiguration jadeConfiguration() {
        JadeConfiguration configuration = new JadeConfiguration();
        configuration.setCaching(false);
        configuration.setTemplateLoader(templateLoader());

        return configuration;
    }

    /**
     * Setup view resolver for Jade templates
     * Using custom view resolver, because Jade is bad
     * @return jade view resolver
     */
    @Bean
    public ViewResolver jadeViewResolver() {
        CustomJadeViewResolver jadeViewResolver = new CustomJadeViewResolver();
        jadeViewResolver.setConfiguration(jadeConfiguration());
        jadeViewResolver.setOrder(0);

        return jadeViewResolver;
    }
}