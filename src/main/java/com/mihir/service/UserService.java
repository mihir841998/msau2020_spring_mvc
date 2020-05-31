package com.mihir.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mihir.model.User;
import com.mihir.dao.UserDao;

@Service
@Transactional(readOnly = true)
public class UserService 
{
		@Autowired
	   private UserDao userDao;
		
		
			
		   public JSONObject check_user_credential(User u)
		   {
			   return userDao.check_user_credential(u);
		   }

//	   @Transactional
//	   public long save(User book) {
//	      return userDao.save(book);
//	   }
//
//	   
//	   public User get(long id) {
//	      return userDao.get(id);
//	   }
//
//	   public List<User> list() {
//	      return userDao.list();
//	   }
//
//	   @Transactional
//	   public void update(long id, User u) {
//	      userDao.update(id, u);
//	   }
//
//	   @Transactional
//	   public void delete(long id) {
//	      userDao.delete(id);
//	   }
}
