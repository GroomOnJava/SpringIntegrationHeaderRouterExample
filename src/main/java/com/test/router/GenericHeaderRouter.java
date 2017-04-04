package com.test.router;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;

import com.google.gson.JsonObject;

@MessageEndpoint
public class GenericHeaderRouter {

	@Router(inputChannel="inputGateway")
	public String genericFlowRouter(Message<JsonObject> message) {
		return message.getHeaders().get("header-flow").toString();
	}
}
