package com.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.test.model.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class AppTest {

	@Autowired
	WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testRead() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/read");
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testWrite() throws Exception {

		Gson gson = new Gson();
		Item item = new Item();
		item.setName("Prasanth");
		item.setAge(29);
		String json = gson.toJson(item);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/write").contentType(MediaType.APPLICATION_JSON)
				.content(json);
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testWritep() throws Exception{
		
		Gson gson = new Gson();
		Item item = new Item();
		item.setName("Prasanth");
		item.setAge(29);
		String json = gson.toJson(item);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/writeparam/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("header-flow", "cafe")
				.param("name", "values")
				.content(json);
		mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print());
		
	}
	
}
