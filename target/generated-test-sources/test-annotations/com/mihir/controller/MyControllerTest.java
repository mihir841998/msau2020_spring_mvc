package com.mihir.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mihir.model.Demand;
import com.mihir.model.Hiringmanager;
import com.mihir.model.Onbordee;
import com.mihir.model.User;
import com.mihir.service.HmService;
import com.mihir.service.OnbordeeService;
import com.mihir.service.UserService;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class MyControllerTest {
	
	@InjectMocks
	private MyController mycontroller;
	
	@Mock
    private OnbordeeService onbordeeService;
	
	@Mock
    private UserService userService;
	
	@Mock
    private HmService hmService;
	
	 @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }
	@Test
	public void testCheck_user_credential() throws Exception 
	{
		JSONObject json = new JSONObject();
		json.put("name", "mihir");
		json.put("access", "1");
		User u = new User();
		u.setEmail("m@m");
		when(userService.check_user_credential(u)).thenReturn(json);
		ResponseEntity<JSONObject> expected = new ResponseEntity<JSONObject>(json,HttpStatus.OK);
		ResponseEntity<JSONObject> actual = mycontroller.check_user_credential(u);
		assertEquals(expected, actual);		
	}

	@Test
	public void testGet_access_for_email() 
	{
		JSONObject json = new JSONObject();
		json.put("name", "mihir");
		json.put("access", "1");
		User u = new User();
		u.setEmail("m@m");
		when(userService.get_access_for_email(u)).thenReturn(json);
		ResponseEntity<JSONObject> expected = new ResponseEntity<JSONObject>(json,HttpStatus.OK);
		ResponseEntity<JSONObject> actual = mycontroller.get_access_for_email(u);
		assertEquals(expected, actual);
		
	}

	@Test
	public void testGet_log_by_id() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("datetime", "5 August");
		json.put("message", "deleted");
		list.add(json);
		when(userService.get_log_by_id(1L)).thenReturn(list);
		ResponseEntity<List<JSONObject>> expected = new ResponseEntity<List<JSONObject>>(list,HttpStatus.OK);
		ResponseEntity<List<JSONObject>> actual = mycontroller.get_log_by_id(1L);
		assertEquals(expected, actual);
	}

	@Test
	public void testList() {
		List<Onbordee> list = new ArrayList<Onbordee>();
		Onbordee o = new Onbordee();
		list.add(o);
		when(onbordeeService.list(1L)).thenReturn(list);
		ResponseEntity<List<Onbordee>> expected = new ResponseEntity<List<Onbordee>>(list,HttpStatus.OK);
		ResponseEntity<List<Onbordee>> actual = mycontroller.list(1L);
		assertEquals(expected, actual);
		
	}

	@Test
	public void testDelete() {
		doNothing().when(onbordeeService).delete(1, 1);
		ResponseEntity<String> expected = new ResponseEntity<String>("Onboarding has been deleted successfully.",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.delete(1,1);
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdate() {
		Onbordee o = new Onbordee();
		doNothing().when(onbordeeService).update(1, 1, o);
		ResponseEntity<String> expected = new ResponseEntity<String>("Onboarding has been updated successfully.",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.update(1,1, o);
		assertEquals(expected, actual);
		
	}

	@Test
	public void testSave() {
		Onbordee o = new Onbordee();
		when(onbordeeService.save(o,1)).thenReturn("Saved Successfully");
		ResponseEntity<String> expected = new ResponseEntity<String>("Saved Successfully",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.save(1, o);
		assertEquals(expected, actual);
	}

	@Test
	public void testGet_trend_skill() throws Exception {
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("skill", "Java");
		json.put("count", "1");
		list.add(json);
		when(onbordeeService.get_trend_skill()).thenReturn(list);
		ResponseEntity<List<JSONObject>> expected = new ResponseEntity<List<JSONObject>>(list,HttpStatus.OK);
		ResponseEntity<List<JSONObject>> actual = mycontroller.get_trend_skill();
		assertEquals(expected, actual);
	}

	@Test
	public void testGet_trend_location() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("location", "Mumbai");
		json.put("count", "1");
		list.add(json);
		when(onbordeeService.get_trend_location()).thenReturn(list);
		ResponseEntity<List<JSONObject>> expected = new ResponseEntity<List<JSONObject>>(list,HttpStatus.OK);
		ResponseEntity<List<JSONObject>> actual = mycontroller.get_trend_location();
		assertEquals(expected, actual);
	}
//
	@Test
	public void testGet_trend_demandid() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("clientname", "MS");
		json.put("count", "1");
		list.add(json);
		when(onbordeeService.get_trend_demandid()).thenReturn(list);
		ResponseEntity<List<JSONObject>> expected = new ResponseEntity<List<JSONObject>>(list,HttpStatus.OK);
		ResponseEntity<List<JSONObject>> actual = mycontroller.get_trend_demandid();
		assertEquals(expected, actual);
	}

	@Test
	public void testGet_trend_hmid() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("name", "Mihir");
		json.put("count", "1");
		list.add(json);
		when(onbordeeService.get_trend_hmid()).thenReturn(list);
		ResponseEntity<List<JSONObject>> expected = new ResponseEntity<List<JSONObject>>(list,HttpStatus.OK);
		ResponseEntity<List<JSONObject>> actual = mycontroller.get_trend_hmid();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testdemand()
	{
		List<Demand> list = new ArrayList<Demand>();
		Demand d = new Demand();
		list.add(d);
		when(onbordeeService.demand()).thenReturn(list);
		ResponseEntity<List<Demand>> expected = new ResponseEntity<List<Demand>>(list,HttpStatus.OK);
		ResponseEntity<List<Demand>> actual = mycontroller.demand();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testhm()
	{
		List<Hiringmanager> list = new ArrayList<Hiringmanager>();
		Hiringmanager d = new Hiringmanager();
		list.add(d);
		when(onbordeeService.hm()).thenReturn(list);
		ResponseEntity<List<Hiringmanager>> expected = new ResponseEntity<List<Hiringmanager>>(list,HttpStatus.OK);
		ResponseEntity<List<Hiringmanager>> actual = mycontroller.hm();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testgetuser() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.put("name", "mihir");
		json.put("email", "mihir@gmail.com");
		json.put("access","1");
		list.add(json);
		when(userService.list()).thenReturn(list);
		ResponseEntity<List<JSONObject>> expected = new ResponseEntity<List<JSONObject>>(list,HttpStatus.OK);
		ResponseEntity<List<JSONObject>> actual = mycontroller.list();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testgethm() {
		List<Hiringmanager> list = new ArrayList<Hiringmanager>();
		Hiringmanager hm = new Hiringmanager();
		list.add(hm);
		when(hmService.list()).thenReturn(list);
		ResponseEntity<List<Hiringmanager>> expected = new ResponseEntity<List<Hiringmanager>>(list,HttpStatus.OK);
		ResponseEntity<List<Hiringmanager>> actual = mycontroller.listhm();
		assertEquals(expected, actual);		
	}
	
	@Test
	public void testDeleteUser() {
		doNothing().when(userService).delete(1, 1);
		ResponseEntity<String> expected = new ResponseEntity<String>("User has been deleted successfully.",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.delete_user(1,1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeleteHm() {
		doNothing().when(hmService).delete(1, 1);
		ResponseEntity<String> expected = new ResponseEntity<String>("Hiring Manager has been deleted successfully.",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.delete_hm(1,1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSaveUser() {
		User u = new User();
		when(userService.save(u,1)).thenReturn("Saved Successfully");
		ResponseEntity<String> expected = new ResponseEntity<String>("Saved Successfully",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.save_user(1, u);
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testSaveHm() {
		Hiringmanager hm = new Hiringmanager();
		when(hmService.save(hm,1)).thenReturn("Saved Successfully");
		ResponseEntity<String> expected = new ResponseEntity<String>("Saved Successfully",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.save_hm(1, hm);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdateUser() {
		User u = new User();
		doNothing().when(userService).update(1, 1, u);
		ResponseEntity<String> expected = new ResponseEntity<String>("User has been updated successfully.",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.update_user(1,1, u);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testUpdateHm() {
		Hiringmanager hm = new Hiringmanager();
		doNothing().when(hmService).update(1, 1, hm);
		ResponseEntity<String> expected = new ResponseEntity<String>("Hiring Manager has been updated successfully.",HttpStatus.OK);
		ResponseEntity<String> actual=(ResponseEntity<String>) mycontroller.update_hm(1,1, hm);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testgetuser_by_id() {
		User u = new User();
		when(userService.get(1)).thenReturn(u);
		ResponseEntity<User> expected = new ResponseEntity<User>(u,HttpStatus.OK);
		ResponseEntity<User> actual = mycontroller.get(1);
		assertEquals(expected, actual);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
