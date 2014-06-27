package it.sevenbits.project.application.configs;

import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * Router Configuration
 */
@Configuration
public class RouterConfig extends RouterConfigurationSupport {

    @Override
    public List<String> listRouteFiles() {
        List<String> listRouteFiles = new ArrayList<>();
        listRouteFiles.add("file:${application.config}/project/routes/application/routes.conf");

        return listRouteFiles;
    }
}