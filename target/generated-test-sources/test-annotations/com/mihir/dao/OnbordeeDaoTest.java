package com.mihir.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mihir.dao.OnbordeeDao;
import com.mihir.model.Onbordee;
import com.mihir.service.OnbordeeService;

import junit.framework.TestCase;

import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.inOrder;
@RunWith(MockitoJUnitRunner.class)
public class OnbordeeDaoTest 
{
	@InjectMocks private OnbordeeDao onbordeeDao;
	  @Mock private SessionFactory sessionFactory;
	  @Mock private Session session;
	  @Mock private Query query;
	  @Mock private IdentifierLoadAccess identifierObj;
	  
	 
	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }

	@Test
	public void testList() 
	{
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
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("from Onbordee")).thenReturn(query);
		when(query.list()).thenReturn(onbordee);
		
		List<Onbordee> l = sessionFactory.getCurrentSession().createQuery("from Onbordee").list();
		assertEquals(onbordee, l);		
	}

	@Test
	public void testDelete() 
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
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.byId(Onbordee.class)).thenReturn(identifierObj);
		when(identifierObj.load(1)).thenReturn(o);
		String s = onbordeeDao.delete(1,1);
		assertEquals("Onbordee Deleted Successfully", s);
				
	}

	@Test
	public void testUpdate() 
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
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.byId(Onbordee.class)).thenReturn(identifierObj);
		when(identifierObj.load(1L)).thenReturn(o);
		Onbordee o1 = new Onbordee();
		o1.setBgc_status("incomplete");
		o1.setDemandid(1);
		o1.setEmail("mmm@gmail.com");
		o1.setEta("1 month");
		o1.setHmid(2);
		o1.setLocation("Mumbai");
		o1.setName("xyz");
		o1.setOnboarding_status("complete");
		o1.setPhone("1234567890");
		o1.setSkill("Java");
		o1.setStart_date("5 August");
		o1.setUserid(1);
		
		String s =onbordeeDao.update(1L,1, o1);
		assertEquals("Onbordee Updated Successfully", s);
		
	}

	@Test
	public void testGet() 
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
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(Onbordee.class, 1L)).thenReturn(o);
		
		Onbordee onbordee = onbordeeDao.get(1L);
		assertEquals(o, onbordee);
		
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
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.save(o)).thenReturn(1);
		
		String s = onbordeeDao.save(o);
		assertEquals("Onbordee saved with USER ID " + o.getUserid(), s);
		
	}

	@Test
	public void testGet_trend_skill() 
	{		
		Object[] objarr = {"java","1"};
		List<Object[]> list =new ArrayList<Object[]>();
		list.add(objarr);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("select skill,count(*) as count from Onbordee group by skill",Object[].class)).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("skill","java");
		json.put("count", "1");
		jsonlist.add(json);
		
		List<JSONObject> actual = onbordeeDao.get_trend_skill();
		assertEquals(jsonlist, actual);		
	}

	@Test
	public void testGet_trend_location() {
		Object[] objarr = {"mumbai","1"};
		List<Object[]> list =new ArrayList<Object[]>();
		list.add(objarr);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("select location,count(*) as count from Onbordee group by location",Object[].class)).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("location","mumbai");
		json.put("count", "1");
		jsonlist.add(json);		
		List<JSONObject> actual = onbordeeDao.get_trend_location();
		assertEquals(jsonlist, actual);	
	}

	@Test
	public void testGet_trend_demandid() {
		Object[] objarr = {"Morgan Stanley","1"};
		List<Object[]> list =new ArrayList<Object[]>();
		list.add(objarr);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("select d.clientname,count(*) as count from Onbordee as o join Demand as d on o.demandid=d.did group by o.demandid",Object[].class)).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("clientname","Morgan Stanley");
		json.put("count", "1");
		jsonlist.add(json);		
		List<JSONObject> actual = onbordeeDao.get_trend_demandid();
		assertEquals(jsonlist, actual);	
	}

	@Test
	public void testGet_trend_hmid() {
		Object[] objarr = {"Mit Shah","1"};
		List<Object[]> list =new ArrayList<Object[]>();
		list.add(objarr);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("select h.name,count(*) as count from Onbordee as o join Hiringmanager as h on o.hmid=h.hmid group by o.hmid",Object[].class)).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("hiring_manager_name","Mit Shah");
		json.put("count", "1");
		jsonlist.add(json);		
		List<JSONObject> actual = onbordeeDao.get_trend_hmid();
		assertEquals(jsonlist, actual);	
	}

//	@Test
//	public void testLog() {
//		fail("Not yet implemented");
//	}

}
