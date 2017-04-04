package com.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.test.gateway.TestGateway;
import com.test.model.Item;

@Controller
@RestController
public class TestController {

	@Autowired
	private TestGateway testGateway;
	
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	@ResponseBody
	public String read() {
		return "test";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String write(@RequestBody Item request) {
		return "test";
	}

	@RequestMapping(value = "/writeparam/{id}", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public String writeParam(@PathVariable("id") Integer id,
			@RequestHeader final Map<String,Object> headers,
			@RequestParam("name")String name,
			@RequestBody Item request) {
		Gson gson = new Gson();
		String jsonRequest=gson.toJson(request);
		JsonObject payload = new JsonParser().parse(jsonRequest).getAsJsonObject();
		Message<JsonObject> message = new GenericMessage<JsonObject>(payload,headers);
		testGateway.process(message);
		return "test";
	}
}
