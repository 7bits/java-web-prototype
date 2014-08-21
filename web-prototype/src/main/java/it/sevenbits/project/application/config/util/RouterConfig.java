package it.sevenbits.project.application.config.util;

import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Router Configuration
 */
@Configuration
public class RouterConfig extends RouterConfigurationSupport {

    @Value("#{systemProperties['project.config']}")
    private String propertyConfig;

    @Override
    public List<String> listRouteFiles() {
        List<String> listRouteFiles = new ArrayList<>();
        // TODO need research: doesn't work ${project.config} in next string. Reason - unknown
        listRouteFiles.add("file:" + propertyConfig + "/routes/application/routes.conf");

        return listRouteFiles;
    }
}