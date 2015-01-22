package it.sevenbits.project.application.config.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Message converter configuration
 */
@Configuration
public class MessageConverterConfig {

    /**
     * Creating list of Message Converters to overwrite default HandlerAdapter list in WebMvcConfig
     * Includes default list + Json Message Converter
     * @return list of message converters
     */
    @Bean(name = "messageConverter")
    public List<HttpMessageConverter<?>> messageConverters() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(stringHttpMessageConverter);
        messageConverters.add(new SourceHttpMessageConverter<>());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        return messageConverters;
    }
}