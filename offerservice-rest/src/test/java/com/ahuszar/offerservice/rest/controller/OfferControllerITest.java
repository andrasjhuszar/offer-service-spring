package com.ahuszar.offerservice.rest.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ahuszar.offerservice.rest.Application;
import com.ahuszar.offerservice.model.repository.OfferRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class OfferControllerITest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	private String VALID_OFFER_JSON = "{\"product\" : 1, \"description\" : \"Nice offer\", \"price\" : 3.45}";
	private String INVALID_OFFER_JSON = "{\"product\" : -1, \"description\" : \"Nice offer\", \"price\" : 3.45}";
	
	@Test
	public void listOffersShouldReturnTheInitialOffer() throws Exception {
		mockMvc.perform(get("/offers/"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].description", is("Banana summer sale")))
            .andExpect(jsonPath("$[0].price", is(2.50)))
            .andExpect(jsonPath("$[0].currency", is("GBP")))
            .andExpect(jsonPath("$[0].product.id", is(1)))
            .andExpect(jsonPath("$[0].product.name", is("banana")));
	}
	
	@Test
	public void createOfferShouldAddANewOffer() throws Exception {
		mockMvc.perform(get("/offers/"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].description", is("Banana summer sale")))
            .andExpect(jsonPath("$[0].price", is(2.50)))
            .andExpect(jsonPath("$[0].currency", is("GBP")))
            .andExpect(jsonPath("$[0].product.id", is(1)))
            .andExpect(jsonPath("$[0].product.name", is("banana")));
		
		mockMvc.perform(post("/offers")
			.content(VALID_OFFER_JSON)
			.contentType(contentType))
			.andExpect(status().isOk());
		
		mockMvc.perform(get("/offers/"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].description", is("Banana summer sale")))
	        .andExpect(jsonPath("$[0].price", is(2.50)))
	        .andExpect(jsonPath("$[0].currency", is("GBP")))
	        .andExpect(jsonPath("$[0].product.id", is(1)))
	        .andExpect(jsonPath("$[0].product.name", is("banana")))
	        .andExpect(jsonPath("$[1].id", is(2)))
	        .andExpect(jsonPath("$[1].description", is("Nice offer")))
	        .andExpect(jsonPath("$[1].price", is(3.45)))
	        .andExpect(jsonPath("$[1].currency", is("GBP")))
	        .andExpect(jsonPath("$[1].product.id", is(1)))
	        .andExpect(jsonPath("$[1].product.name", is("banana")));
		
		offerRepository.delete(2l);
	}
	
	@Test
	public void createOfferShouldReturnNotFoundIfProductIsWrong() throws Exception {
		mockMvc.perform(post("/offers")
				.content(INVALID_OFFER_JSON)
				.contentType(contentType))
				.andExpect(status().isNotFound());
	}
}
