package com.WL.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "key")
public class ConfigProperty {

    private String riot;
    private String test;

    // standard getters and setters
}