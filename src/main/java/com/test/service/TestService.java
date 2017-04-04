package com.test.service;

import org.springframework.messaging.Message;

import com.google.gson.JsonObject;
import com.test.model.Item;

public interface TestService {

	public void read();
	
	public Item  write();
	
	public void handleMesg(Message<JsonObject> message);
}
