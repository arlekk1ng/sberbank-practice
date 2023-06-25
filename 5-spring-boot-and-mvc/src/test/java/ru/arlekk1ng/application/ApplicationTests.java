package ru.arlekk1ng.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Test that context can be successfully loads.")
	void contextLoads() {
	}

	@Test
	@DisplayName("Test that /blog page can be successfully called.")
	void testPageRequestAndContent() throws Exception {
		mockMvc.perform(get("/blog")).andExpect(status().isOk());
	}

}
