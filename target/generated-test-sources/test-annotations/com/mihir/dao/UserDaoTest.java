package com.mihir.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mihir.dao.OnbordeeDao;
import com.mihir.model.Onbordee;
import com.mihir.model.User;
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
import org.springframework.boot.jackson.JsonObjectDeserializer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.mockito.Mockito.inOrder;
@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest 
{
	@InjectMocks private UserDao userDao;
	  @Mock private SessionFactory sessionFactory;
	  @Mock private Session session;
	  @Mock private Query query;
	  @Mock private IdentifierLoadAccess identifierObj;
	  
	 
	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }

	

	@Test
	public void testCheck_user_credential() {
		User u =new User();
		u.setAccess("1");
		u.setId(1L);
		u.setName("mihir");
		u.setPassword("mihir");
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(User.class, 1L)).thenReturn(u);
		User u2 =new User();
		u2.setAccess("1");
		u2.setId(1L);
		u2.setName("mihir");
		u2.setPassword("mihi");
		JSONObject json = new JSONObject();
		json.put("result", "success");
		json.put("access", "1");
		json.put("name", "mihir");
		json.put("id", 1L);
		JSONObject json_actual = userDao.check_user_credential(u);
		assertEquals(json, json_actual);
		json.clear();
		json.put("result", "failure");
		json.put("access", "-1");
		json_actual = userDao.check_user_credential(u2);
		assertEquals(json, json_actual);
	}
	
	@Test
	public void get_log_by_id_test()
	{
		String q = "select l.datetime,l.message from Logs as l where message like \'%" + 5323 + "%\'";
		List<JSONObject> l = new ArrayList<JSONObject>();
		JSONObject obj = new JSONObject();
	      obj.put("datetime","5 August 2020");
	      obj.put("operation","deleted");
	      l.add(obj);
	      List<Object[]> objlist = new ArrayList<Object[]>();
	      Object object[] = {"5 August 2020","deleted"};
	      objlist.add(object);
	      when(sessionFactory.getCurrentSession()).thenReturn(session);
	      when(session.createQuery(q)).thenReturn(query);
	      when(query.list()).thenReturn(objlist);
	      List<JSONObject> actual = userDao.get_log_by_id(5323L);
	      assertEquals(l, actual);	
		
	}
	
	@Test
	public void testList() 
	{
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();
		json.put("id", 1);
		json.put("name", "mihir");
		json.put("email", "mihir@gmail.com");
		json.put("access","1");
		list.add(json);
		
		Object[] objarr = {1,"mihir","1","mihir@gmail.com"};
		List<Object[]> list1 =new ArrayList<Object[]>();
		list1.add(objarr);
		
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery("select id,name,access,email from User",Object[].class)).thenReturn(query);
		when(query.getResultList()).thenReturn(list1);
		
		List<JSONObject> actual = userDao.list();
		
		assertEquals(list,actual);		
	}
	
	
	@Test
	public void testSave() 
	{
		User u = new User();
		u.setId(1L);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.save(u)).thenReturn(1L);
		
		String s = userDao.save(u);
		assertEquals("User saved with USER ID " + u.getId(), s);		
	}
	
	@Test
	public void testGet() 
	{
		User u = new User();
		u.setId(1L);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(User.class, 1L)).thenReturn(u);
		
		User actual = userDao.get(1L);
		assertEquals(u, actual);
		
	}
	
	@Test
	public void testUpdate() 
	{
		User u = new User();
		u.setId(1L);
		u.setName("mihir");
		u.setEmail("M@M");
		u.setPassword("m");
		u.setAccess("1");
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.byId(User.class)).thenReturn(identifierObj);
		when(identifierObj.load(1L)).thenReturn(u);
		User u1 = new User();
		u1.setId(1L);
		u1.setName("mihirr");
		u1.setEmail("M@M");
		u1.setPassword("m");
		u1.setAccess("1");
		String s =userDao.update(1L,1, u1);
		assertEquals("User Updated Successfully", s);
		
	}
	
	@Test
	public void testDelete() 
	{
		User u = new User();
		u.setId(1L);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.byId(User.class)).thenReturn(identifierObj);
		when(identifierObj.load(1)).thenReturn(u);
		String s = userDao.delete(1,1);
		assertEquals("User Deleted Successfully", s);
				
	}
	

	

}
