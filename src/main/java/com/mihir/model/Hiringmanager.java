package com.mihir.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Hiringmanager")
public class Hiringmanager 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hmid;
	private String name;
	private String email;
	private String phone;
	public Long getHmid() {
		return hmid;
	}
	public void setHmid(Long hmid) {
		this.hmid = hmid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Hiringmanager [hmid=" + hmid + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	
	

}
