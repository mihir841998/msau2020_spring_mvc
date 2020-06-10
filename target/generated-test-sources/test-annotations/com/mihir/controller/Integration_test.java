package com.mihir.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mihir.config.AppConfig;
import com.mihir.model.Onbordee;
import com.mihir.service.OnbordeeService;

import antlr.Utils;
import junit.framework.Assert;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class Integration_test {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void givenWac_whenServletContext_thenItProvidesGreetController() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("MyController"));
	}

	@Test
	public void check_demand() throws Exception {
	    this.mockMvc.perform(get("api/demand")
	      .accept(MediaType.APPLICATION_JSON))	       
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.*", is(3)));
	}
	
	@Test
	public void check_hmid() throws Exception {
	    this.mockMvc.perform(get("api/hmid")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.*", is(4)));
	}
	
	@Test
	public void user_credential() throws Exception {
		String s = "{\n"+"\"id\":\"5321\"\n"+"\"password\":\"mihir\"\n"+"}";
	    this.mockMvc.perform(post("api/check")	
	      .content(s))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.name", is("mihir")))
	      .andExpect(jsonPath("$.access", is("2")))
	      .andExpect(jsonPath("$.id", is("5321")))
	      .andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	public void update() throws Exception {
		String s = "{\n"+
						"\"bgc_status\":\"Complete\"\n"+
						"\"demandid\":\"1\"\n"+
						"\"email\":\"anc@gmail.com\"\n"+
						"\"eta\":\"1 Month\"\n"+
						"\"hmid\":\"2\"\n"+
						"\"location\":\"Mumbai\"\n"+
						"\"name\":\"mihir\"\n"+
						"\"onborading_status\":\"Complete\"\n"+
						"\"phone\":\"741258960\"\n"+
						"\"skill\":\"Java\"\n"+
						"\"start_date\":\"5 July,2020\"\n"+
						"\"userid\":\"1015\"\n"+
					"}";
		
	    this.mockMvc.perform(put("api/onbordee/1015/5323")	
	      .content(s))
	      .andExpect(status().isOk())
	      .andExpect(content().string("Onboarding has been updated successfully."));
	}
	
	@Test
	public void save() throws Exception {
		String s = "{\n"+
						"\"bgc_status\":\"Complete\"\n"+
						"\"demandid\":\"1\"\n"+
						"\"email\":\"anc@gmail.com\"\n"+
						"\"eta\":\"1 Month\"\n"+
						"\"hmid\":\"2\"\n"+
						"\"location\":\"Mumbai\"\n"+
						"\"name\":\"mihir\"\n"+
						"\"onborading_status\":\"Complete\"\n"+
						"\"phone\":\"741258960\"\n"+
						"\"skill\":\"Java\"\n"+
						"\"start_date\":\"5 July,2020\"\n"+
						"\"userid\":\"1015\"\n"+
					"}";
		
	    this.mockMvc.perform(post("api/onbordee/5323")	
	      .content(s))
	      .andExpect(status().isOk())
	      .andExpect(content().string("Onbordee saved with USER ID 1015"));
	}
	
	@Test
	public void delete() throws Exception {
			
	    this.mockMvc.perform(MockMvcRequestBuilders.delete("api/onbordee/1015/5323"))
	      .andExpect(status().isOk())
	      .andExpect(content().string("Onboarding has been deleted successfully."));
	}
	
	
	@Test
	public void get_access_by_email() throws Exception {
		String s = "{\n"+"\"email\":\"mihirjayesh.maniar@accoliteindia.com\"\n"+"}";
	    this.mockMvc.perform(post("api/getaccess")	
	      .content(s))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.name", is("mihir")))
	      .andExpect(jsonPath("$.access", is("2")))
	      .andExpect(jsonPath("$.id", is("5321")));
	}
	
	@Test
	public void get_log_by_id() throws Exception {
	    this.mockMvc.perform(get("api/log/5321")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.length()", is(2)));
	}
	
	@Test
	public void get_trendskill() throws Exception {
	    this.mockMvc.perform(get("api/trendskill"))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.length()", is(2)));
	}
	
	@Test
	public void get_trendlocation() throws Exception {
	    this.mockMvc.perform(get("api/trendlocation"))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.length()", is(2)));
	}
	
	@Test
	public void get_trendhmid() throws Exception {
	    this.mockMvc.perform(get("api/trendhmid"))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.length()", is(2)));
	}
	
	@Test
	public void get_trenddemadid() throws Exception {
	    this.mockMvc.perform(get("api/trenddemandid"))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$.length()", is(2)));
	}
	
	@Test
	public void get_onbordee() throws Exception {
	    this.mockMvc.perform(get("api/onbordee/5321"))
	      .andExpect(status().isOk());	      
	}
}
