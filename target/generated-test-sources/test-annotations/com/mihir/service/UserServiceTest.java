package com.mihir.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mihir.dao.OnbordeeDao;
import com.mihir.dao.UserDao;
import com.mihir.model.Onbordee;
import com.mihir.model.User;

import junit.framework.TestCase;

public class UserServiceTest extends TestCase 
{
	@InjectMocks private UserService userService;
	  @Mock private UserDao userDao;
	  
	 
	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }
	  @Test
	public void testCheck_user_credential() 
	{
		  JSONObject json =new JSONObject();
			json.put("result", "success");
			json.put("access", "1");
			
			User u =new User();
			u.setAccess("1");
			u.setId(1L);
			u.setName("abc");
			u.setPassword("abc");
			
			when(userDao.check_user_credential(u)).thenReturn(json);
			JSONObject j = userService.check_user_credential(u);
			assertEquals(json, j);
			
		
	}
	  
	 @Test
	 public void get_access_by_email()
	 {
		 JSONObject obj = new JSONObject();
		 obj.put("name","mihir");
		 User u = new User();
		  u.setEmail("mihir@gmail.com");
		  when(userDao.get_access_for_email(u)).thenReturn(obj);
		  JSONObject actual = userService.get_access_for_email(u);
		  assertEquals(obj, actual);
		 
	 }
	 
	 @Test
		public void testList() {
			List<JSONObject> list = new ArrayList<JSONObject>();
			JSONObject json = new JSONObject();
			json.put("name", "mihir");
			list.add(json);
			when(userDao.list()).thenReturn(list);
			List<JSONObject> actual = userService.list();		
			assertEquals(list,actual);
		}
	 
	 @Test
		public void testUpdate() 
		{
		 User u = new User();
			when(userDao.update(1, 1,u)).thenReturn("updated");
			userService.update(1, 1, u);
			verify(userDao,times(1)).update(1,1,u);		
		}
	 
	 @Test
	public void testDelete() {
	 User u = new User();
	when(userDao.delete(1, 1)).thenReturn("deleted");
	userService.delete(1, 1);
	verify(userDao,times(1)).delete(1,1);
	}
	 
	 @Test
		public void testSave() 
		{
		 User u = new User();
		 u.setId(1L);
		when(userDao.save(u)).thenReturn("User saved with USER ID 1");
		String s = userService.save(u,1L);
		assertEquals("User saved with USER ID 1", s);
		}
	 
	 @Test
		public void testGet() {
		 User u = new User();
		 u.setId(1L);
		when(userDao.get(1)).thenReturn(u);
		User a = userService.get(1);
			assertEquals(u, a);
		}
		
	  
	

	  

}
