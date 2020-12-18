package com.artarkatesoft.coronavirustracker.xray;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class XRayConfig {

    @Bean
    public Filter TracingFilter() {
        return new AWSXRayServletFilter("HomeVersion");
    }

//    @Bean
//    public Filter TracingFilter() {
//        return new AWSXRayServletFilter(new DynamicSegmentNamingStrategy("MyApp", "*.example.com"));
//    }
}
