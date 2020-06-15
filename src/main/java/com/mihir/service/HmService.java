package com.mihir.service;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mihir.model.Demand;
import com.mihir.model.Hiringmanager;
import com.mihir.model.Onbordee;
import com.mihir.model.User;
import com.mihir.dao.HmDao;
import com.mihir.dao.OnbordeeDao;
import com.mihir.dao.UserDao;

@Service
@Transactional(readOnly = true)
public class HmService 
{
	@Autowired
	private HmDao hmDao;
	
	@Transactional
	public List<Hiringmanager> list() 
	{
	      return hmDao.list();	   
	}
	
	@Transactional
	public void update(long id, long userid, Hiringmanager u) 
	{
	      hmDao.update(id,userid, u);
	}
	
	@Transactional
	public void delete(long id,long userid) 
	{
	      hmDao.delete(id,userid);
	}
	
	@Transactional
	public String save(Hiringmanager o,long userid) 
	{
	log("Info", "New " + o + " created and saved with Id "+ o.getHmid()+" by userid "+userid);
      return hmDao.save(o);
   }
	
	@Transactional
	   public void log(String level,String message) {
	      hmDao.log(level,message);
	   }

}
