package org.example.config;

import org.example.service.EngineService;
import org.example.service.TerminalService;
import org.example.service.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = "org.example")
@Configuration
public class Spring_Config {

}
