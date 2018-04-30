package com.routes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.routes.controller.RouteController;
import com.routes.service.impl.RouteFinderServiceImpl;
import com.routes.service.impl.RouteUploaderServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RouteControllerTest {

	private RouteController routeController = new RouteController(new RouteFinderServiceImpl(), new RouteUploaderServiceImpl());
	
	private MockMvc mockMvc;


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();

	}

	@Test
	public void testIsConnected() throws Exception {
		this.mockMvc.perform(get("/connected?origin=Boston&destination=New%20York")).andExpect(status().isOk());
	}

}
