package com.grca.games.soldiers.controller.web;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.grca.games.soldiers.model.User;
import com.grca.games.soldiers.model.dto.UserDto;
import com.grca.games.soldiers.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PagesControllerTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private PagesController controller;
	@Autowired
	private UserService userService;

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
		userService.save(new User(1L, "user", "password"));
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
	
	@Test
	public void testRegisterInvalidUser() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/register").flashAttr("user", new UserDto("re", "password", "password")).with(csrf()))
					.andExpect(status().isBadRequest())
					.andExpect(view().name("auth/register"));
	}
	
	@Test
	public void testRegisterMismatchedPasswordsUser() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/register").flashAttr("user", new UserDto("register", "password1", "password2")).with(csrf()))
					.andExpect(status().isBadRequest())
					.andExpect(view().name("auth/register"));
	}
	
	@Test
	public void testRegisterValidUser() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/register").flashAttr("user", new UserDto("register", "password", "password")).with(csrf()))
					.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testRegisterDuplicateUser() throws Exception {
		userService.save(new User(null, "duplicate", "password"));
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/register").flashAttr("user", new UserDto("duplicate", "password", "password")).with(csrf()))
					.andExpect(status().isBadRequest())
					.andExpect(view().name("auth/register"));
	}
	
	@Test
	@WithMockUser
	public void testLoggedInRegisterUser() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/register").flashAttr("user", new UserDto("loggedin", "password", "password")).with(csrf()))
					.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testRegisterWithoutCsrf() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		mockMvc.perform(post("/register").flashAttr("user", new UserDto("register", "password", "password")))
					.andExpect(status().isForbidden());
	}

}
