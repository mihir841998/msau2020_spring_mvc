package com.mihir.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mihir.model.Onbordee;
import com.mihir.dao.OnbordeeDao;
import com.mihir.dao.UserDao;

@Service
@Transactional(readOnly = true)
public class OnbordeeService 
{
	@Autowired
	private OnbordeeDao onbordeeDao;
	@Transactional
	public List<Onbordee> list() 
	{
		  log("Info", "get all onbordees");
	      return onbordeeDao.list();	   
	}
	
	@Transactional
	public void update(long id, Onbordee u) 
	{
		  log("Info", "onbordee with id "+ id +"is updated");
	      onbordeeDao.update(id, u);
	}
	
	@Transactional
	public void delete(long id) 
	{
	log("Info", "onbordee with id "+ id +"is deleted");
	      onbordeeDao.delete(id);
	}
	@Transactional
	public Onbordee get(long id)
	{
		log("Info", "get onbordee with id "+ id);
		 return onbordeeDao.get(id);
	}
	@Transactional
   public String save(Onbordee o) 
	{
	log("Info", "new onbordee created and saved with UserId"+ o.getUserid());
      return onbordeeDao.save(o);
   }
	
	@Transactional
	public List<JSONObject> get_trend_skill()
	{
		log("Info", "get trends on skill");
		return onbordeeDao.get_trend_skill();
	}
	
	@Transactional
	public List<JSONObject> get_trend_location()
	{
		log("Info", "get trends on locations");
		return onbordeeDao.get_trend_location();
	}
	
	@Transactional
	public List<JSONObject> get_trend_demandid()
	{
		log("Info", "get trends on demandid");
		return onbordeeDao.get_trend_demandid();
	}
	@Transactional
	public List<JSONObject> get_trend_hmid()
	{
		log("Info", "get trends on hirng manger id");
		return onbordeeDao.get_trend_hmid();
	}
	
	@Transactional
	   public void log(String level,String message) {
	      onbordeeDao.log(level,message);
	   }
	
	

}
