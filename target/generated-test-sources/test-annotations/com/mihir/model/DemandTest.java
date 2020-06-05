package com.mihir.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DemandTest {

	@Test
	public void testGetDid() {
		Demand d =new Demand();
		d.setDid(1L);
		long id = d.getDid();
		assertEquals(1L, id);
	}

	@Test
	public void testSetDid() 
	{
		Demand d =new Demand();
		d.setDid(1L);
		long id = d.getDid();
		assertEquals(1L, id);		
	}

	@Test
	public void testGetClientname() {
		Demand d =new Demand();
		d.setClientname("Morgan Stanley");
		String s = d.getClientname();
		assertEquals("Morgan Stanley", s);
	}

	@Test
	public void testSetClientname() {
		Demand d =new Demand();
		d.setClientname("Morgan Stanley");
		String s = d.getClientname();
		assertEquals("Morgan Stanley", s);
	}

	@Test
	public void testGetRole() {
		Demand d =new Demand();
		d.setRole("Java");
		String s = d.getRole();
		assertEquals("Java", s);
	}

	@Test
	public void testSetRole() {
		Demand d =new Demand();
		d.setRole("Java");
		String s = d.getRole();
		assertEquals("Java", s);
	}
	
	@Test
	public void testToString() {
		Demand d =new Demand();
		d.setClientname("MS");
		d.setDid(1L);
		d.setRole("Java");
		String s = "Demand [did=" + 1 + ", clientname=" + "MS" + ", role=" + "Java" + "]";
		String actual = d.toString();
		assertEquals(s, actual);
	}
	

	

}
