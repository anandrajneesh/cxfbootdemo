package org.blog.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Anand_Rajneesh on 3/23/2017.
 */
@Configuration
public class RestServer {

    @Bean
    public JacksonJsonProvider jsonProvider(){
        return new JacksonJsonProvider();
    }

    @Bean
    public LoggingFeature loggingFeature(){
        return new LoggingFeature();
    }

    @Bean
    public Swagger2Feature swagger(){
        return new Swagger2Feature();
    }
}
