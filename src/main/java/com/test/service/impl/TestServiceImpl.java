package com.test.service.impl;

import org.springframework.messaging.Message;

import com.google.gson.JsonObject;
import com.test.model.Item;
import com.test.service.TestService;

public class TestServiceImpl implements TestService{

	public void read() {
		System.out.println("Hi...");
	}

	public Item write() {
		Item item=new Item();
		
		return item;
	}

	@Override
	public void handleMesg(Message<JsonObject> message) {
		System.out.println(message.getPayload());
		
	}
}
