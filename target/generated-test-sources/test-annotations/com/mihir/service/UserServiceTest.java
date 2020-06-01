package com.mihir.service;

import static org.mockito.Mockito.when;

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

//	public void testLog() {
//		fail("Not yet implemented");
//	}

}
