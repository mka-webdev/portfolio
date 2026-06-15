package com.uni.app.Application.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Add support for iTunes API response format
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(
                org.springframework.http.MediaType.APPLICATION_JSON,
                org.springframework.http.MediaType.valueOf("text/javascript;charset=utf-8")));
        restTemplate.getMessageConverters().add(0, converter);

        return restTemplate;
    }
}
