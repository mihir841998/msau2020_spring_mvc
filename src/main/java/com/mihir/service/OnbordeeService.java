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
	public List<Onbordee> list() 
	{
	      return onbordeeDao.list();	   
	}
	
	@Transactional
	public void update(long id, Onbordee u) 
	{
	      onbordeeDao.update(id, u);
	}
	
	@Transactional
	public void delete(long id) 
	{
	      onbordeeDao.delete(id);
	}
	
	public Onbordee get(long id)
	{
		 return onbordeeDao.get(id);
	}
	@Transactional
   public String save(Onbordee o) {
      return onbordeeDao.save(o);
   }
	
	
	public List<JSONObject> get_trend_skill()
	{
		return onbordeeDao.get_trend_skill();
	}
	

}
