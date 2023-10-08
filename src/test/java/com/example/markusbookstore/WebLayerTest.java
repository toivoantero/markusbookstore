package com.example.markusbookstore;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

/**
 * Testing the web layer
 * 
 * Spring application context is started, but without the server
 * 
 * @author h01270
 *
 */
//@ExtendWith(SpringExtension.class) not needed when using JUnit5
@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLoginPage() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Please sign in")));
	}
	
	@Test
	@WithMockUser(username = "user", password = "user")
	public void testRestBooks() throws Exception {
		this.mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Vilma")));
	}
	
	@Test
	@WithMockUser(username = "user", password = "user")
	public void testRestBookId() throws Exception {
		this.mockMvc.perform(get("/book/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Vilma")));
	}

}
