package com.mihir.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Demand")
public class Demand 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long did;
	private String clientname;
	private String role;
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Demand [did=" + did + ", clientname=" + clientname + ", role=" + role + "]";
	}
	
	

}
