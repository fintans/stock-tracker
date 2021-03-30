package com.bigbank.stocktracker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bigbank.stocktracker.model.User;
import com.bigbank.stocktracker.service.CustomUserDetailsService;
import com.bigbank.stocktracker.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	Logger logger = LogManager.getLogger(UserControllerTest.class);

	@Autowired
	private MockMvc mvc;

	@MockBean
	UserService userService;

	@MockBean
	CustomUserDetailsService userDetailsService;

	@Test
    public void whenUserRegistersAssertUserJsonReturned() throws Exception {
    	User user = new User("admin", "password");
    	when(userService.register(any())).thenReturn(user);
   
        this.mvc.perform(post("/api/users/register")
        	      .contentType(MediaType.APPLICATION_JSON)
        	      .content("{\"username\": \"admin\",\"password\": \"pass\"}"))
        	      .andExpect(status().isOk())
        	      .andExpect(jsonPath("$.username", is("admin")));
    }

}
