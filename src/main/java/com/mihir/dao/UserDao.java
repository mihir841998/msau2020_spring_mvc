package com.mihir.dao;

import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.mihir.model.Logs;
import com.mihir.model.User;

@Repository
public class UserDao 
{
	@Autowired
	private SessionFactory sessionFactory;
//	private static final Logger logger = LogManager.getLogger(UserDao.class);
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
		      obj.put("name", user.getName());
		      obj.put("id",user.getId());
//		      logger.info("Authentication success for user with ID"+u.getId());
		      return obj;
		}
	}
	
	public JSONObject get_access_for_email(User u)
	{
		List<Object[]> ulist =(List<Object[]>) sessionFactory.getCurrentSession().createQuery("select u.name,u.id,"
				+ "u.access from User as u where u.email= :email").setParameter("email", u.getEmail()).list();
		Object[] o = ulist.get(0);
		JSONObject obj = new JSONObject();
	      obj.put("name",o[0]);
	      obj.put("access",o[2]);
	      obj.put("id", o[1]);
	      return obj;
	}
	public List<JSONObject> get_log_by_id(Long id)
	{
		String query = "select l.datetime,l.message from Logs as l where message like \'%" + id + "%\'";
		List<Object[]> list =(List<Object[]>) sessionFactory.getCurrentSession().createQuery(query).list();
		List<JSONObject> l = new ArrayList<JSONObject>();
		for(int i=0;i<list.size();i++)
		{
			Object[] o = list.get(i);
			JSONObject obj = new JSONObject();
		      obj.put("datetime",o[0]);
		      obj.put("operation",o[1]);
		      l.add(obj);
		}
		System.out.println(l);
	      return l;
	}
	
	public void log(String level,String message)
	   {
		   Logs l = new Logs();
		   l.setLevel(level);
		   l.setMessage(message);
		   l.setClassname("OnbordeeDao");
		   l.setDatetime(LocalDateTime.now());
		   
		   sessionFactory.getCurrentSession().save(l);  
		   
	   }
	


}
