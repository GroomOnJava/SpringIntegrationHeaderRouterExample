package com.test;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.controller.TestController;
import com.test.service.TestService;
import com.test.service.impl.TestServiceImpl;

@Configuration
@ComponentScan(basePackages="com.test")
@EnableAutoConfiguration
@IntegrationComponentScan
public class AppConfig extends WebMvcConfigurationSupport {

	/**************************************************************************************************************************/
	// This bean will convert the HttpRequest of type json to corresponding POJO in @requestmapping.  
	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customJackson2HttpMessageConverter());
		super.addDefaultHttpMessageConverters(converters);
	}

	/**************************************************************************************************************************/

	@Bean
	public TestService testService() {
		return new TestServiceImpl();
	}

	@Bean
	public TestController testController() {
		return new TestController();
	}
	
	@Bean
	public DirectChannel inputGateway() {
		return new DirectChannel();
	}
	
	@Bean
	public DirectChannel movie() {
		return new DirectChannel();
	}
	
	@Bean
	public DirectChannel cafe() {
		return new DirectChannel();
	}
}
