package com.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    @Autowired
    public JerseyConfig(ObjectMapper objectMapper) {
        packages("com.employee.controller");
    }

}