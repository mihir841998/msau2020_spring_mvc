package com.mihir.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mihir.model.Onbordee;
import com.mihir.model.User;
import com.mihir.service.OnbordeeService;
import com.mihir.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MyController 
{
	@Autowired
	private UserService userService;
	@Autowired
	private OnbordeeService onbordeeService;
	
   @PostMapping("/check")
   public ResponseEntity<JSONObject> check_user_credential(@RequestBody User user)
   {
      JSONObject obj = userService.check_user_credential(user);
      return ResponseEntity.ok().body(obj);
   }
   
   @GetMapping("/onbordee")
   public ResponseEntity<List<Onbordee>> list() {
      List<Onbordee> onbordees = onbordeeService.list();
      return ResponseEntity.ok().body(onbordees);
   }
   
   @DeleteMapping("/onbordee/{id}")
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
      onbordeeService.delete(id);
      return ResponseEntity.ok().body("Onboarding has been deleted successfully.");
   }
   
   @PutMapping("/onbordee/{id}")
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Onbordee book) {
      onbordeeService.update(id, book);
      return ResponseEntity.ok().body("Onboarding has been updated successfully.");
   }
   
   @GetMapping("/onbordee/{id}")
   public ResponseEntity<Onbordee> get(@PathVariable("id") long id) {
      Onbordee o = onbordeeService.get(id);
      return ResponseEntity.ok().body(o);      
   }
   
   @PostMapping("/onbordee")
   public ResponseEntity<?> save(@RequestBody Onbordee o) {
	  System.out.println("In MyController" + o);
      String s = onbordeeService.save(o);
      return ResponseEntity.ok().body(s);
   }
   
   @GetMapping("/trendskill")
   public ResponseEntity<List<JSONObject>> get_trend_skill() {
      List<JSONObject> skill = onbordeeService.get_trend_skill();
      return ResponseEntity.ok().body(skill);
   }
   
   
   
   
   
   
   
   
		
}
