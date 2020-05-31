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

import com.mihir.model.Onbordee;
import com.mihir.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Repository
public class OnbordeeDao 
{
	private static final Logger logger = LogManager.getLogger(OnbordeeDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Onbordee> list()
	{
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
   
   
	
	
	
	

}
