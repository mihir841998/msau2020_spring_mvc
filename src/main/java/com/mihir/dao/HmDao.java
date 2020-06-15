package com.mihir.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mihir.model.Demand;
import com.mihir.model.Hiringmanager;
import com.mihir.model.Logs;
import com.mihir.model.Onbordee;
import com.mihir.model.User;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   


@Repository
public class HmDao 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Hiringmanager> list()
	{
		List<Hiringmanager> list = sessionFactory.getCurrentSession().createQuery("from Hiringmanager").list();

		return list;
	}
	public String delete(long id,long userid) 
   {
      Hiringmanager u = sessionFactory.getCurrentSession().byId(Hiringmanager.class).load(id);
      sessionFactory.getCurrentSession().delete(u);
      log("Info", u+" with id "+ id +" is deleted by userID " + userid);
      return "Hiring Manager Deleted Successfully";
   }
	
	@Transactional
	   public void log(String level,String message)
	   {
		   System.out.println("in onbordee log");
		   Logs l = new Logs();
		   l.setLevel(level);
		   l.setMessage(message);
		   l.setClassname("HmDao");
		   l.setDatetime(LocalDateTime.now());		   
		   sessionFactory.getCurrentSession().save(l); 		   
	   }
	
	public String save(Hiringmanager hm)
   {
	   sessionFactory.getCurrentSession().save(hm);
	   return "Hiringmanager saved with ID " + hm.getHmid();	   
   }
	
	public String update(long id, long userid, Hiringmanager o)
	   {
		   Session session = sessionFactory.getCurrentSession();
		   Hiringmanager odetails = session.byId(Hiringmanager.class).load(id);
		   String s = "UserID "+userid+" updated Hiring Manager with ID "+ o.getHmid();
		   List<String> list = new ArrayList<String>();
		    if(!o.getEmail().equals(odetails.getEmail()))
		   {
			   list.add("changed email from "+odetails.getEmail()+" to "+o.getEmail());
		   }
		   if(o.getHmid()!=odetails.getHmid())
		   {
			   list.add("changed Hiring manager ID from "+odetails.getHmid()+" to "+o.getHmid());
		   }
		   if(!o.getName().equals(odetails.getName()))
		   {
			   list.add("changed Name from "+odetails.getName()+" to "+o.getName());
		   }
		   if(!o.getPhone().equals(odetails.getPhone()))
		   {
			   list.add("changed Phone from "+odetails.getPhone()+" to "+o.getPhone());
		   }
		   s = s +" "+ list;
		   log("Info",s);
		   odetails.setHmid(o.getHmid());
		   odetails.setName(o.getName());
		   odetails.setPhone(o.getPhone());
		   odetails.setEmail(o.getEmail());
		   session.flush();
		   return "Hiring Manager Updated Successfully";
	   }
	

}
