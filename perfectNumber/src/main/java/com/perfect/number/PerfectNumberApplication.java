package com.perfect.number;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PerfectNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfectNumberApplication.class, args);
	}
	
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
	    JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
	    factory.setPort(8080);
	    factory.setContextPath("/api");
	    return factory;
	}
}
