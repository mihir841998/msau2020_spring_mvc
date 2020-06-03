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
		JSONObject json_actual = userDao.check_user_credential(u);
		assertEquals(json, json_actual);
		json.clear();
		json.put("result", "failure");
		json.put("access", "-1");
		json_actual = userDao.check_user_credential(u2);
		assertEquals(json, json_actual);
	}

//	@Test
//	public void get_access_by_email_test() 
//	{
//		List<Object[]>list=new ArrayList<Object[]>();
//		Object[] obj = {"mihir","2"};
//		list.add(obj);
//		when(sessionFactory.getCurrentSession()).thenReturn(session);
//		when(session.createQuery("select u.name,u.access from User as u where u.email= :email").setParameter("email", "mihirjayesh.maniar@accoliteindia.com")).thenReturn(query);
//		when(query.list()).thenReturn(list);
//		JSONObject expected = new JSONObject();
//	     expected.put("name","mihir");
//	     expected.put("access","2");
//	     User user = new User();
//	     user.setEmail("mihirjayesh.maniar@accoliteindia.com");
//	     user.setId(1L);
//	     user.setName("mihir");
//	     user.setAccess("access");
//	     user.setPassword("mihir");
//	     JSONObject actual = userDao.get_access_for_email(user);
//	     assertEquals(expected, actual);		
//	}

}
