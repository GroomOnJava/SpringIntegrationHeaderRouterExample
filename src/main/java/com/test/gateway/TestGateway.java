package com.test.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
 

@MessagingGateway
@Component
public interface TestGateway {

	@Gateway(requestChannel="inputGateway")
	public void process(Message<JsonObject> message);
}
