package com.mihir.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mihir.model.Onbordee;
import com.mihir.model.User;
import com.mihir.dao.UserDao;

@Service
@Transactional(readOnly = true)
public class UserService 
{
		@Autowired
	   private UserDao userDao;
		
		@Transactional
		public List<JSONObject> list() 
		{
//			  log("Info", "All users viewed by " + id);
		      return userDao.list();	   
		}
		
		
		@Transactional
		   public JSONObject check_user_credential(User u)
		   {
			System.out.println("in user service check_user_credential");
			   log("info","User with Id "+ u.getId()+" logged in to the system");
			   return userDao.check_user_credential(u);
		   }
		
		@Transactional
		   public JSONObject get_access_for_email(User u)
		   {
//			   log("info","Access of  emailId"+ u.getEmail() + "fetched from database");
			   return userDao.get_access_for_email(u);
		   }
		
		public List<JSONObject> get_log_by_id(Long id)
		{
			return userDao.get_log_by_id(id);
		}
		   
		   @Transactional
		   public void log(String level,String message) {
		      userDao.log(level,message);
		   }
		   
		   @Transactional
			public void update(long id, long userid, User u) 
			{
//				  log("Info", "onbordee with id "+ id +" is updated by id "+userid);
			      userDao.update(id,userid, u);
			}
		   
		   @Transactional
			public void delete(long id,long userid) 
			{
//			log("Info", "Onbordee with id "+ id +" is deleted by userid " + userid);
			      userDao.delete(id,userid);
			}
		   @Transactional
			public User get(long id)
			{
//				log("Info", "get onbordee with id "+ id);
				 return userDao.get(id);
			}
		   @Transactional
		   public String save(User o,long userid) 
			{
			log("Info", "New " + o + " created and saved with UserId "+ o.getId()+" by userid "+userid);
		      return userDao.save(o);
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
