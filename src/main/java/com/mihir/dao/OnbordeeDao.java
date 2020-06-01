package com.mihir.dao;

import org.springframework.stereotype.Repository;

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
	
	public String delete(long id) 
   {
      Onbordee u = sessionFactory.getCurrentSession().byId(Onbordee.class).load(id);
      sessionFactory.getCurrentSession().delete(u);	
//      logger.info("Deleted Onbordee with id"+ id);
      return "Onbordee Deleted Successfully";
   }
	
   public String update(long id, Onbordee o)
   {
	   Session session = sessionFactory.getCurrentSession();
	   Onbordee odetails = session.byId(Onbordee.class).load(id);
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
