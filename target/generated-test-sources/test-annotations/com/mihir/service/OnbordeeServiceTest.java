package com.mihir.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mihir.dao.OnbordeeDao;
import com.mihir.model.Demand;
import com.mihir.model.Hiringmanager;
import com.mihir.model.Onbordee;

import junit.framework.TestCase;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
@RunWith(MockitoJUnitRunner.class)
public class OnbordeeServiceTest extends TestCase {
	@InjectMocks private OnbordeeService onbordeeService;
	  @Mock private OnbordeeDao onbordeeDao;
	  
	 
	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }
	  @Test
	public void testList() {
		List<Onbordee> onbordee = new ArrayList<Onbordee>();
		Onbordee o = new Onbordee();
		o.setBgc_status("incomplete");
		o.setDemandid(1);
		o.setEmail("abc@gmail.com");
		o.setEta("1 month");
		o.setHmid(2);
		o.setLocation("Mumbai");
		o.setName("abc");
		o.setOnboarding_status("complete");
		o.setPhone("1234567890");
		o.setSkill("Java");
		o.setStart_date("5 August");
		o.setUserid(1);
		onbordee.add(o);
		when(onbordeeDao.list()).thenReturn(onbordee);
		List<Onbordee> o_list = onbordeeService.list(1L);		
		assertEquals(o,o_list.get(0));
	}
	 @Test
	public void testUpdate() 
	{
		Onbordee o = new Onbordee();
		when(onbordeeDao.update(1, 1,o)).thenReturn("updated");
		onbordeeService.update(1, 1, o);
		verify(onbordeeDao,times(1)).update(1,1,o);		
	}
	 @Test
	public void testDelete() {
		Onbordee o = new Onbordee();
		when(onbordeeDao.delete(1, 1)).thenReturn("deleted");
		onbordeeService.delete(1, 1);
		verify(onbordeeDao,times(1)).delete(1,1);
	}
	@Test
	public void testdemand()
	{
		Demand d = new Demand();
		List<Demand>expected = new ArrayList<Demand>();
		expected.add(d);
		when(onbordeeDao.demand()).thenReturn(expected);
		List<Demand>actual=onbordeeService.demand();
		assertEquals(expected, actual);		
	}
	@Test
	public void testhm()
	{
		Hiringmanager d = new Hiringmanager();
		List<Hiringmanager>expected = new ArrayList<Hiringmanager>();
		expected.add(d);
		when(onbordeeDao.hm()).thenReturn(expected);
		List<Hiringmanager>actual=onbordeeService.hm();
		assertEquals(expected, actual);		
	}
	@Test
	public void testGet() {
		Onbordee o = new Onbordee();
		o.setBgc_status("incomplete");
		o.setDemandid(1);
		o.setEmail("abc@gmail.com");
		o.setEta("1 month");
		o.setHmid(2);
		o.setLocation("Mumbai");
		o.setName("abc");
		o.setOnboarding_status("complete");
		o.setPhone("1234567890");
		o.setSkill("Java");
		o.setStart_date("5 August");
		o.setUserid(1);
		when(onbordeeDao.get(1)).thenReturn(o);
		Onbordee a = onbordeeService.get(1);
		assertEquals(o, a);
	}
	@Test
	public void testSave() 
	{
		Onbordee o = new Onbordee();
		o.setBgc_status("incomplete");
		o.setDemandid(1);
		o.setEmail("abc@gmail.com");
		o.setEta("1 month");
		o.setHmid(2);
		o.setLocation("Mumbai");
		o.setName("abc");
		o.setOnboarding_status("complete");
		o.setPhone("1234567890");
		o.setSkill("Java");
		o.setStart_date("5 August");
		o.setUserid(1);
		when(onbordeeDao.save(o)).thenReturn("Onbordee saved with USER ID 1");
		String s = onbordeeService.save(o,1L);
		assertEquals("Onbordee saved with USER ID 1", s);
		
	}
	@Test
	public void testGet_trend_skill() {
		JSONObject json =new JSONObject();
		json.put("skill", "Java");
		json.put("count", 1);
		List<JSONObject> list = new ArrayList<JSONObject>();
		list.add(json);
		when(onbordeeDao.get_trend_skill()).thenReturn(list);
		List<JSONObject> l = onbordeeService.get_trend_skill();
		assertEquals(list, l);
	}
	@Test
	public void testGet_trend_location() {
		JSONObject json =new JSONObject();
		json.put("lcation", "Mumbai");
		json.put("count", 1);
		List<JSONObject> list = new ArrayList<JSONObject>();
		list.add(json);
		when(onbordeeDao.get_trend_location()).thenReturn(list);
		List<JSONObject> l = onbordeeService.get_trend_location();
		assertEquals(list, l);
	}
	@Test
	public void testGet_trend_demandid() {
		JSONObject json =new JSONObject();
		json.put("clientname", "Morgan Stanley");
		json.put("count", 1);
		List<JSONObject> list = new ArrayList<JSONObject>();
		list.add(json);
		when(onbordeeDao.get_trend_demandid()).thenReturn(list);
		List<JSONObject> l = onbordeeService.get_trend_demandid();
		assertEquals(list, l);
	}
	@Test
	public void testGet_trend_hmid() {
		JSONObject json =new JSONObject();
		json.put("hiring_manager_name", "Mit shah");
		json.put("count", 1);
		List<JSONObject> list = new ArrayList<JSONObject>();
		list.add(json);
		when(onbordeeDao.get_trend_hmid()).thenReturn(list);
		List<JSONObject> l = onbordeeService.get_trend_hmid();
		assertEquals(list, l);
	}
	@Test
	public void testLog() {
		doNothing().when(onbordeeDao).log("info","mihir");
		onbordeeService.log("info", "mihir");
		verify(onbordeeDao,times(1)).log("info","mihir");	
	}

}
