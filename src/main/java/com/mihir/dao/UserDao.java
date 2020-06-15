package com.mihir.dao;

import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
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
import com.mihir.model.Onbordee;
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
	      log("info","User with Id "+ o[1]+" logged in to the system");
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
		   l.setClassname("UserDao");
		   l.setDatetime(LocalDateTime.now());
		   
		   sessionFactory.getCurrentSession().save(l);		   
	   }
	
	public List<JSONObject> list()
	{
		 List<Object[]> results =(List<Object[]>) sessionFactory.getCurrentSession()
				   .createQuery("select id,name,access,email from User",Object[].class).getResultList();
		   List<JSONObject> list = new LinkedList<JSONObject>();
		   for (int i = 0; i < results.size(); i++) 
			  {
				  Object[] o = results.get(i); // for first element!
//				  String skill = (String) o[0]; 			  // choose the correct type!
//				  System.out.println(o[0]);
//				  System.out.println(o[1]);
//				  String count = (String) o[1];
				  JSONObject obj = new JSONObject();
				  obj.put("id", o[0]);
				  obj.put("name", o[1]);
				  obj.put("email", o[3]);
				  obj.put("access", o[2]);				  
				  list.add(obj);			  
			  }
		   System.out.println(list);
		   return list;
	}
	
	public String save(User u)
   {
	   sessionFactory.getCurrentSession().save(u);
	   return "User saved with USER ID " + u.getId();	   
   }
	
	public User get(long id) 
	   {
		      return sessionFactory.getCurrentSession().get(User.class, id);
	   }
	
	public String delete(long id,long userid) 
	   {
	      User u = sessionFactory.getCurrentSession().byId(User.class).load(id);
	      sessionFactory.getCurrentSession().delete(u);	
//	      logger.info("Deleted Onbordee with id"+ id);
	      log("Info", u+" with id "+ id +" is deleted by userid " + userid);
	      return "User Deleted Successfully";
	   }
	
	public String update(long id, long userid, User u)
	   {
		   Session session = sessionFactory.getCurrentSession();
		   User udetails = session.byId(User.class).load(id);
		   u.setPassword(udetails.getPassword());
		   String s = "UserID "+userid+" updated User with ID "+ u.getId();
		   List<String> list = new ArrayList<String>();
		   System.out.println("in userdaio update "+ u.getId()+"  "+ udetails.getId());
		   if(!u.getId().equals(udetails.getId()))
		   {
			   list.add("changed UserID from " + udetails.getId() + " to " + u.getId());
		   }
		   if(!u.getEmail().equals(udetails.getEmail()))
		   {
			   list.add("changed Email from " + udetails.getEmail() + " to " + u.getEmail());
		   }
		   if(!u.getAccess().equals(udetails.getAccess()))
		   {
			   list.add("changed Access from " + udetails.getAccess() + " to " + u.getAccess());
		   }
		   if(!u.getName().equals(udetails.getName()))
		   {
			   list.add("changed Name from " + udetails.getName() + " to " + u.getName());
		   }
		   s = s +" "+ list;
		   log("Info",s);
		   udetails.setAccess(u.getAccess());
		   udetails.setEmail(u.getEmail());
		   udetails.setId(u.getId());
		   udetails.setName(u.getName());
		   udetails.setPassword(u.getPassword());
		   session.flush();
//		   logger.info("Onbordee Updated with id"+id);
		   return "User Updated Successfully";
	   }
	


}
