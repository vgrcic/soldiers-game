package com.grca.games.soldiers.controller.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grca.games.soldiers.model.Soldier;
import com.grca.games.soldiers.model.User;
import com.grca.games.soldiers.service.SoldierService;
import com.grca.games.soldiers.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SoldierControllerTest {
	
	@Autowired
	private SoldierController controller;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private SoldierService service;
	@Autowired
	private UserService userService;
	
	private MockMvc mockMvc;
	private ObjectMapper json = new ObjectMapper();

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
	
	@Test
	@WithMockUser(value="player")
	public void testSaveSoldier() throws Exception {
		String soldier = json.writeValueAsString(new Soldier(null, "createdSoldier", null));
		mockMvc.perform(post("/api/soldier/")
				.contentType(MediaType.APPLICATION_JSON).content(soldier)
				.with(csrf())).andExpect(status().isCreated());
	}
	
	@Test
	@WithMockUser(value="playen")
	public void testSaveSoldierBadUsername() throws Exception {
		String soldier = json.writeValueAsString(new Soldier(null, "createdSoldier", null));
		mockMvc.perform(post("/api/soldier/")
				.contentType(MediaType.APPLICATION_JSON).content(soldier)
				.with(csrf())).andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(value="player")
	public void testSaveSoldierShortName() throws Exception {
		String soldier = json.writeValueAsString(new Soldier(null, "cr", null));
		mockMvc.perform(post("/api/soldier/")
				.contentType(MediaType.APPLICATION_JSON).content(soldier)
				.with(csrf())).andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(value="player")
	public void testSaveSoldierDuplicate() throws Exception {
		service.save(new Soldier(null, "duplicate", null), "player");
		String soldier = json.writeValueAsString(new Soldier(null, "duplicate", null));
		mockMvc.perform(post("/api/soldier/")
				.contentType(MediaType.APPLICATION_JSON).content(soldier)
				.with(csrf())).andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(value="user")
	public void testSaveSoldierNotDuplicate() throws Exception {
		userService.save(new User(null, "user", "password"));
		String soldier = json.writeValueAsString(new Soldier(null, "duplicate", null));
		mockMvc.perform(post("/api/soldier/")
				.contentType(MediaType.APPLICATION_JSON).content(soldier)
				.with(csrf())).andExpect(status().isCreated());
	}
	
	@Test
	@WithMockUser(value="user")
	public void testGetForUser() throws Exception {
		mockMvc.perform(get("/api/soldier/").with(csrf())).andExpect(status().isOk());
	}

}
