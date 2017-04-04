package com.test.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.stereotype.Component;

import com.test.service.TestService;

@Component
@EnableIntegration
@IntegrationComponentScan
public class MovieIntegrationFlow {

	@Autowired
	TestService testService;
	
	@Bean
	public IntegrationFlow movieFlow() {
		return IntegrationFlows.from("movie")
				.publishSubscribeChannel(
						c->c.subscribe(
									sf->sf.handle(testService,"handleMesg")
								)
						)
				.get();
	}

}
