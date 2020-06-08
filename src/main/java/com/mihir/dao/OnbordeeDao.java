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
public class OnbordeeDao 
{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//static Logger log = Logger.getLogger(OnbordeeDao.class);
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	
	public List<Onbordee> list()
	{
//		PropertyConfigurator.configure("src/main/resources/log4j.properties");
//		log.info("get onboardees");
		List<Onbordee> list = sessionFactory.getCurrentSession().createQuery("from Onbordee").list();
//		logger.info("Get Onboordees");
		return list;
	}
	
	public String delete(long id,long userid) 
   {
      Onbordee u = sessionFactory.getCurrentSession().byId(Onbordee.class).load(id);
      sessionFactory.getCurrentSession().delete(u);	
//      logger.info("Deleted Onbordee with id"+ id);
      log("Info", u+" with id "+ id +" is deleted by userid " + userid);
      return "Onbordee Deleted Successfully";
   }
	
   public String update(long id, long userid, Onbordee o)
   {
	   Session session = sessionFactory.getCurrentSession();
	   Onbordee odetails = session.byId(Onbordee.class).load(id);
	   String s = "UserID "+userid+" updated Onbordee with ID "+ o.getUserid();
	   List<String> list = new ArrayList<String>();
	   if(!o.getBgc_status().equals(odetails.getBgc_status()))
	   {
		   list.add("changed bgc_status from "+odetails.getBgc_status()+" to "+o.getBgc_status());
	   }
	   if(o.getDemandid()!=odetails.getDemandid())
	   {
		   list.add("changed demandid from "+odetails.getDemandid()+" to "+o.getDemandid());
	   }
	   if(!o.getEmail().equals(odetails.getEmail()))
	   {
		   list.add("changed email from "+odetails.getEmail()+" to "+o.getEmail());
	   }
	   if(!o.getEta().equals(odetails.getEta()))
	   {
		   list.add("changed ETA for arrival from "+odetails.getEta()+" to "+o.getEta());
	   }
	   if(o.getHmid()!=odetails.getHmid())
	   {
		   list.add("changed Hiring manager ID from "+odetails.getHmid()+" to "+o.getHmid());
	   }
	   if(!o.getLocation().equals(odetails.getLocation()))
	   {
		   list.add("changed location from "+odetails.getLocation()+" to "+o.getLocation());
	   }
	   if(!o.getName().equals(odetails.getName()))
	   {
		   list.add("changed Name from "+odetails.getName()+" to "+o.getName());
	   }
	   if(!o.getOnboarding_status().equals(odetails.getOnboarding_status()))
	   {
		   list.add("changed Onboarding Status from "+odetails.getOnboarding_status()+" to "+o.getOnboarding_status());
	   }
	   if(!o.getPhone().equals(odetails.getPhone()))
	   {
		   list.add("changed Phone from "+odetails.getPhone()+" to "+o.getPhone());
	   }
	   if(!o.getSkill().equals(odetails.getSkill()))
	   {
		   list.add("changed Skill from "+odetails.getSkill()+" to "+o.getSkill());
	   }
	   if(!o.getStart_date().equals(odetails.getStart_date()))
	   {
		   list.add("changed Start Date from "+odetails.getStart_date()+" to "+o.getStart_date());
	   }
	   if(o.getUserid()!=odetails.getUserid())
	   {
		   list.add("changed UserID from "+odetails.getUserid()+" to "+o.getUserid());
	   }
	   s = s +" "+ list;
	   log("Info",s);
	   odetails.setBgc_status(o.getBgc_status());
	   odetails.setDemandid(o.getDemandid());
	   odetails.setEta(o.getEta());
	   odetails.setHmid(o.getHmid());
	   odetails.setLocation(o.getLocation());
	   odetails.setOnboarding_status(o.getOnboarding_status());
	   odetails.setSkill(o.getSkill());
	   odetails.setStart_date(o.getStart_date());
	   odetails.setName(o.getName());
	   odetails.setPhone(o.getPhone());
	   odetails.setEmail(o.getEmail());
	   session.flush();
//	   logger.info("Onbordee Updated with id"+id);
	   return "Onbordee Updated Successfully";
   }
   
   public Onbordee get(long id) 
   {
	      return sessionFactory.getCurrentSession().get(Onbordee.class, id);
   }
   
   public String save(Onbordee o)
   {
	   sessionFactory.getCurrentSession().save(o);
	   return "Onbordee saved with USER ID " + o.getUserid();	   
   }
   
   public List<JSONObject> get_trend_skill()
   {
	   List<Object[]> results =(List<Object[]>) sessionFactory.getCurrentSession()
			   .createQuery("select skill,count(*) as count from Onbordee group by skill",Object[].class).getResultList();
	   List<JSONObject> list = new LinkedList<JSONObject>();
	   for (int i = 0; i < results.size(); i++) 
		  {
			  Object[] o = results.get(i); // for first element!
//			  String skill = (String) o[0]; 			  // choose the correct type!
//			  System.out.println(o[0]);
//			  System.out.println(o[1]);
//			  String count = (String) o[1];
			  JSONObject obj = new JSONObject();
			  obj.put("skill", o[0]);
			  obj.put("count", o[1]);
			  list.add(obj);			  
		  }
	   System.out.println(list);
	   return list;   
   }
   
   public List<JSONObject> get_trend_location()
   {
	   List<Object[]> results =(List<Object[]>) sessionFactory.getCurrentSession()
			   .createQuery("select location,count(*) as count from Onbordee group by location",Object[].class).getResultList();
	   List<JSONObject> list = new LinkedList<JSONObject>();
	   for (int i = 0; i < results.size(); i++) 
		  {
			  Object[] o = results.get(i); // for first element!
//			  String skill = (String) o[0]; 			  // choose the correct type!
//			  System.out.println(o[0]);
//			  System.out.println(o[1]);
//			  String count = (String) o[1];
			  JSONObject obj = new JSONObject();
			  obj.put("location", o[0]);
			  obj.put("count", o[1]);
			  list.add(obj);			  
		  }
	   System.out.println(list);
	   return list;   
   }
   
   public List<JSONObject> get_trend_demandid()
   {
	   List<Object[]> results =(List<Object[]>) sessionFactory.getCurrentSession()
			   .createQuery("select d.clientname,count(*) as count from Onbordee as o join Demand as d on o.demandid=d.did group by o.demandid",Object[].class).getResultList();
	   List<JSONObject> list = new LinkedList<JSONObject>();
	   for (int i = 0; i < results.size(); i++) 
		  {
			  Object[] o = results.get(i); // for first element!
//			  String skill = (String) o[0]; 			  // choose the correct type!
//			  System.out.println(o[0]);
//			  System.out.println(o[1]);
//			  String count = (String) o[1];
			  JSONObject obj = new JSONObject();
			  obj.put("clientname", o[0]);
			  obj.put("count", o[1]);
			  list.add(obj);			  
		  }
	   System.out.println(list);
	   return list;   
   }
   
   public List<JSONObject> get_trend_hmid()
   {
	   List<Object[]> results =(List<Object[]>) sessionFactory.getCurrentSession()
			   .createQuery("select h.name,count(*) as count from Onbordee as o join Hiringmanager as h on o.hmid=h.hmid group by o.hmid",Object[].class).getResultList();
	   List<JSONObject> list = new LinkedList<JSONObject>();
	   for (int i = 0; i < results.size(); i++) 
		  {
			  Object[] o = results.get(i); // for first element!
//			  String skill = (String) o[0]; 			  // choose the correct type!
//			  System.out.println(o[0]);
//			  System.out.println(o[1]);
//			  String count = (String) o[1];
			  JSONObject obj = new JSONObject();
			  obj.put("hiring_manager_name", o[0]);
			  obj.put("count", o[1]);
			  list.add(obj);			  
		  }
	   System.out.println(list);
	   return list;   
   }
   
   public List<Demand> demand()
	{
//		PropertyConfigurator.configure("src/main/resources/log4j.properties");
//		log.info("get onboardees");
		List<Demand> list = sessionFactory.getCurrentSession().createQuery("from Demand").list();
//		logger.info("Get Onboordees");
		return list;
	}
   
   public List<Hiringmanager> hm()
	{
//		PropertyConfigurator.configure("src/main/resources/log4j.properties");
//		log.info("get onboardees");
		List<Hiringmanager> list = sessionFactory.getCurrentSession().createQuery("from Hiringmanager").list();
//		logger.info("Get Onboordees");
		return list;
	}
   
   
   
   
   @Transactional
   public void log(String level,String message)
   {
	   System.out.println("in onbordee log");
	   Logs l = new Logs();
	   l.setLevel(level);
	   l.setMessage(message);
	   l.setClassname("OnbordeeDao");
	   l.setDatetime(LocalDateTime.now());
	   
	   sessionFactory.getCurrentSession().save(l); 
	   
   }
   
   
	
	
	
	

}
