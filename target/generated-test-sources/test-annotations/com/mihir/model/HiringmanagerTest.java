package com.mihir.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class HiringmanagerTest {

	@Test
	public void testGetHmid() {
		Hiringmanager h = new Hiringmanager();
		h.setHmid(1L);
		long id = h.getHmid();
		assertEquals(1L,id);
	}

	@Test
	public void testSetHmid() {
		Hiringmanager h = new Hiringmanager();
		h.setHmid(1L);
		long id = h.getHmid();
		assertEquals(1L,id);
	}

	@Test
	public void testGetName() {
		Hiringmanager h = new Hiringmanager();
		h.setName("M");
		String s = h.getName();
		assertEquals("M",s);
	}

	@Test
	public void testSetName() {
		Hiringmanager h = new Hiringmanager();
		h.setName("M");
		String s = h.getName();
		assertEquals("M",s);
	}

	@Test
	public void testGetEmail() {
		Hiringmanager h = new Hiringmanager();
		h.setEmail("M@m");
		String s = h.getEmail();
		assertEquals("M@m",s);
	}

	@Test
	public void testSetEmail() {
		Hiringmanager h = new Hiringmanager();
		h.setEmail("M@m");
		String s = h.getEmail();
		assertEquals("M@m",s);
	}

	@Test
	public void testGetPhone() {
		Hiringmanager h = new Hiringmanager();
		h.setPhone("7894561230");
		String s = h.getPhone();
		assertEquals("7894561230",s);
	}

	@Test
	public void testSetPhone() {
		Hiringmanager h = new Hiringmanager();
		h.setPhone("7894561230");
		String s = h.getPhone();
		assertEquals("7894561230",s);
	}

	@Test
	public void testToString() {
		Hiringmanager h = new Hiringmanager();
		h.setEmail("m@m");
		h.setHmid(1L);
		h.setName("m");
		h.setPhone("7894561230");
		
		String s =  "Hiringmanager [hmid=" + 1 + ", name=" + "m" + ", email=" + "m@m" + ", phone=" + "7894561230" + "]";
		String actual = h.toString();
		
		assertEquals(s, actual);
		
	}

}
