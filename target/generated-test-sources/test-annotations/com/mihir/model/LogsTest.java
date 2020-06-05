package com.mihir.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class LogsTest {

	@Test
	public void testGetId() {
		Logs l = new Logs();
		l.setId(1L);
		long id = l.getId();
		assertEquals(1L, id);
	}

	@Test
	public void testSetId() {
		Logs l = new Logs();
		l.setId(1L);
		long id = l.getId();
		assertEquals(1L, id);
	}

	@Test
	public void testGetClassname() {
		Logs l = new Logs();
		l.setClassname("M");
		String i = l.getClassname();
		assertEquals("M", i);
	}

	@Test
	public void testSetClassname() {
		Logs l = new Logs();
		l.setClassname("M");
		String i = l.getClassname();
		assertEquals("M", i);
	}

	@Test
	public void testGetDatetime() {
		Logs l = new Logs();
		LocalDateTime d = LocalDateTime.now();
		l.setDatetime(d);
		LocalDateTime actual=l.getDatetime();
		assertEquals(d, actual);
	}

	@Test
	public void testSetDatetime() {
		Logs l = new Logs();
		LocalDateTime d = LocalDateTime.now();
		l.setDatetime(d);
		LocalDateTime actual=l.getDatetime();
		assertEquals(d, actual);
	}

	@Test
	public void testGetLevel() {
		Logs l = new Logs();
		l.setLevel("Info");
		String i = l.getLevel();
		assertEquals("Info", i);
	}

	@Test
	public void testSetLevel() {
		Logs l = new Logs();
		l.setLevel("Info");
		String i = l.getLevel();
		assertEquals("Info", i);
	}

	@Test
	public void testGetMessage() {
		Logs l = new Logs();
		l.setMessage("Info");
		String i = l.getMessage();
		assertEquals("Info", i);
	}

	@Test
	public void testSetMessage() {
		Logs l = new Logs();
		l.setMessage("Info");
		String i = l.getMessage();
		assertEquals("Info", i);
	}

	@Test
	public void testToString() {
		Logs l = new Logs();
		LocalDateTime d = LocalDateTime.now();
		l.setDatetime(d);
		l.setId(1L);
		l.setLevel("Info");
		l.setMessage("info");
		l.setClassname("L");
		String s = "Logs [id=" + 1L + ", classname=" + "L" + ", datetimel=" + d + ", level=" + "Info"
				+ ", message=" + "info" + "]";
		String actual = l .toString();
		assertEquals(s, actual);
	}

}
