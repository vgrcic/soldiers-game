package com.grca.games.soldiers.controller.web;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(get("/"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrlPattern("**/login"));
	}
	
	@Test
	@WithMockUser
	public void testLoggedInIndexPage() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(get("/"))
					.andExpect(status().isOk())
					.andExpect(view().name("index"));
	}
	
	@Test
	public void testLoginPage() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(get("/login"))
					.andExpect(status().isOk())
					.andExpect(view().name("auth/login"));
	}
	
	@Test
	@WithMockUser
	public void testLoggedInLoginPage() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(get("/login"))
					.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testLoginWithInvalidCredentials() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/perform_login")
							.param("username", "user")
							.param("password", "notapassword")
							.with(csrf()))
					.andExpect(status().isOk());
	}
	
	@Test
	public void testLoginWithValidCredentials() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/perform_login")
							.param("username", "user")
							.param("password", "password")
							.with(csrf()))
					.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testLoginWithoutCsrf() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/perform_login")
							.param("username", "user")
							.param("password", "password"))
					.andExpect(status().isForbidden());
	}
	
	@Test
	public void testRegisterPage() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(get("/register"))
					.andExpect(status().isOk())
					.andExpect(view().name("auth/register"));
	}
	
	@Test
	@WithMockUser
	public void testLoggedInRegisterPage() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(get("/register"))
					.andExpect(status().is3xxRedirection());
	}

}
