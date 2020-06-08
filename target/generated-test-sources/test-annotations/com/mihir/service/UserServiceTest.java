package com.mihir.service;

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
	  
	

	  
//	  @Test
//	  public void get_log_by_id_test()
//	  {
//		  JSONObject obj = new JSONObject();
//		  obj.put("datetime", "5 August 2020");
//		  obj.put("message", "deleteg user");
//		  List<JSONObject> list = new ArrayList<JSONObject>();
//		  list.add(obj);
//		  when(userDao.get_log_by_id(1L)).thenReturn(list);
//		  List<JSONObject> list_actual = userService.get_log_by_id(1L);
//		  assertEquals(list, list_actual);
//	  }

}
