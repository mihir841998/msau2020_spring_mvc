package com.mihir.dao;

import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.mihir.model.User;

@Repository
public class UserDao 
{
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LogManager.getLogger(UserDao.class);
	public JSONObject check_user_credential(User u)
	{
		String password = u.getPassword();
		System.out.println("password"+u);
		User user = sessionFactory.getCurrentSession().get(User.class, u.getId());
		System.out.println(user);
		if(!password.equals(user.getPassword()))
		{
		  JSONObject obj = new JSONObject();
	      obj.put("result", "failure");
	      obj.put("access", "-1");	
	      //logger.info("Authentication failed for user with ID"+u.getId());
	      return obj;
		}
		else
		{
			JSONObject obj = new JSONObject();
		      obj.put("result", "success");
		      obj.put("access", user.getAccess());	
//		      logger.info("Authentication success for user with ID"+u.getId());
		      return obj;
		}
	}
	


}
