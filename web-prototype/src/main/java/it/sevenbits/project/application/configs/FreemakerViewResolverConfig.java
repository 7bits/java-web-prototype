//package it.sevenbits.project.application.configs;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//
///**
// * Freemaker view resolver Configuration
// */
//@Configuration
//public class FreemakerViewResolverConfig {
//
//    @Value("#{systemProperties['project.config']}")
//    private String propertyConfig;
//
//    @Bean
//    public FreeMarkerConfigurer freeMarkerConfigurer() {
//        FreeMarkerConfigurer fmc = new FreeMarkerConfigurer();
//        fmc.setTemplateLoaderPath("file:" + propertyConfig + "/view-templates/application/");
//        return fmc;
//    }
//
//    @Bean
//    public FreeMarkerViewResolver freeMarkerViewResolver() {
//        FreeMarkerViewResolver fvr = new FreeMarkerViewResolver();
//        fvr.setCache(false);
//        fvr.setPrefix("");
////        fvr.setSuffix(".ftl");
//        fvr.setRequestContextAttribute("rc");
//        return fvr;
//    }
//}