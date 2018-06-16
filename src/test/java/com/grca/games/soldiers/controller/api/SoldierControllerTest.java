package com.grca.games.soldiers.controller.api;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SoldierControllerTest {
	
	@Autowired
	private SoldierController controller;
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void test() {
		assertNotNull(controller);
	}
	
	@Test
	@WithMockUser(value="player")
	public void testGetSoldier() throws Exception {
		mockMvc.perform(get("/api/soldier/1").with(csrf()))
					.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	@WithMockUser(value="user")
	public void testGetSoldierBadUsername() throws Exception {
		mockMvc.perform(get("/api/soldier/1").with(csrf()))
					.andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(value="player")
	public void testGetNonExistantSoldier() throws Exception {
		mockMvc.perform(get("/api/soldier/0").with(csrf()))
					.andExpect(status().isNotFound());
	}

}
