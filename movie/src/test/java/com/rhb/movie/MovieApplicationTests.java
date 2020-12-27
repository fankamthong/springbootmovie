package com.rhb.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.rhb.movie.controller.MovieController;

/**
 * Mock Test for web layer
 * @author fankamthong
 */
@SpringBootTest
@AutoConfigureMockMvc
class MovieApplicationTests {

	@Autowired
	private MockMvc mockMvc;
    
	/**
	 * Test retrieving all movies
	 * @throws Exception
	 */
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/movies")).andDo(print()).andExpect(status().isOk());
	}	

}
