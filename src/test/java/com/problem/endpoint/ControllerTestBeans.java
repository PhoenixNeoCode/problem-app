package com.problem.endpoint;

import com.problem.Problem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerTestBeans {


    @Bean
    public Problem soapEndpoint() {
        return new SoapEndpoint();
    }
}
