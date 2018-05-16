package com.grca.games.soldiers.controller.web;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PagesControllerTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private PagesController controller;

	@Test
	public void testAutowiring() {
		assertNotNull(controller);
	}
	
	@Test
	public void testIndexPage() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

}
