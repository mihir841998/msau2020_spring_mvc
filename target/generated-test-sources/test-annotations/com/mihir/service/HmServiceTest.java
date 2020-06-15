package com.mihir.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mihir.dao.HmDao;
import com.mihir.dao.UserDao;
import com.mihir.model.Hiringmanager;
import com.mihir.model.Onbordee;
import com.mihir.model.User;
@RunWith(MockitoJUnitRunner.class)
public class HmServiceTest {
	
	@InjectMocks private HmService hmService;
	  @Mock private HmDao hmDao;
	  
	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }

	@Test
	public void testList() {
		List<Hiringmanager> list = new ArrayList<Hiringmanager>();
		Hiringmanager hm = new Hiringmanager();
		list.add(hm);
		
		when(hmDao.list()).thenReturn(list);
		List<Hiringmanager> actual = hmService.list();		
		assertEquals(list,actual);
	}

	@Test
	public void testUpdate() {
		Hiringmanager hm = new Hiringmanager();
		when(hmDao.update(1, 1,hm)).thenReturn("updated");
		hmService.update(1, 1, hm);
		verify(hmDao,times(1)).update(1,1,hm);
	}

	@Test
	public void testDelete() {
		Hiringmanager hm = new Hiringmanager();
		when(hmDao.delete(1, 1)).thenReturn("deleted");
		hmService.delete(1, 1);
		verify(hmDao,times(1)).delete(1,1);
	}

	@Test
	public void testSave() {
		Hiringmanager hm = new Hiringmanager();
		 hm.setHmid(1L);
		when(hmDao.save(hm)).thenReturn("Hiringmanager saved with ID 1");
		String s = hmService.save(hm,1L);
		assertEquals("Hiringmanager saved with ID 1", s);
	}

//	@Test
//	public void testLog() {
//		fail("Not yet implemented");
//	}

}
