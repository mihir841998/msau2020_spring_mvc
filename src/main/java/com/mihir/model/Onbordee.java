package com.mihir.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Onbordee")
public class Onbordee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userid;
	private long demandid;
	private long hmid;
	private String skill;
	private String location;
	private String start_date;
	private String eta;
	private String bgc_status;
	private String onboarding_status;
	private String name;
	private String phone;
	private String email;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getDemandid() {
		return demandid;
	}
	public void setDemandid(long demandid) {
		this.demandid = demandid;
	}
	public long getHmid() {
		return hmid;
	}
	public void setHmid(long hmid) {
		this.hmid = hmid;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getBgc_status() {
		return bgc_status;
	}
	public void setBgc_status(String bgc_status) {
		this.bgc_status = bgc_status;
	}
	public String getOnboarding_status() {
		return onboarding_status;
	}
	public void setOnboarding_status(String onboarding_status) {
		this.onboarding_status = onboarding_status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Onbordee [userid=" + userid + ", demandid=" + demandid + ", hmid=" + hmid + ", skill=" + skill
				+ ", location=" + location + ", start_date=" + start_date + ", eta=" + eta + ", bgc_status="
				+ bgc_status + ", onboarding_status=" + onboarding_status + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + "]";
	}
	
	

}
