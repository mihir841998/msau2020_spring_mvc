package com.mihir.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mihir.model.Hiringmanager;
@RunWith(MockitoJUnitRunner.class)
public class HmDaoTest {
	
	@InjectMocks private HmDao hmDao;
	  @Mock private SessionFactory sessionFactory;
	  @Mock private Session session;
	  @Mock private Query query;
	  @Mock private IdentifierLoadAccess identifierObj;

	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }
	  
	  @Test
	public	void testList() {
			List<Hiringmanager> list = new ArrayList<Hiringmanager>();
			Hiringmanager hm = new Hiringmanager();
			list.add(hm);		
			when(sessionFactory.getCurrentSession()).thenReturn(session);
			when(session.createQuery("from Hiringmanager")).thenReturn(query);
			when(query.list()).thenReturn(list);
			
			List<Hiringmanager> l = hmDao.list();
			assertEquals(list, l);
		}

		@Test
		public void testDelete() {
			Hiringmanager hm = new Hiringmanager();
			hm.setHmid(1L);
			when(sessionFactory.getCurrentSession()).thenReturn(session);
			when(session.byId(Hiringmanager.class)).thenReturn(identifierObj);
			when(identifierObj.load(1)).thenReturn(hm);
			String s = hmDao.delete(1,1);
			assertEquals("Hiring Manager Deleted Successfully", s);
		}

		
		@Test
		public void testSave() {
			Hiringmanager hm = new Hiringmanager();
			hm.setHmid(1L);
			when(sessionFactory.getCurrentSession()).thenReturn(session);
			when(session.save(hm)).thenReturn(1L);
			
			String s = hmDao.save(hm);
			assertEquals("Hiringmanager saved with ID " + hm.getHmid(), s);
		}

		@Test
		public void testUpdate() {
			Hiringmanager hm = new Hiringmanager();
			hm.setEmail("m@m");
			hm.setName("mihir");
			hm.setPhone("8369021457");
			hm.setHmid(1L);
			when(sessionFactory.getCurrentSession()).thenReturn(session);
			when(session.byId(Hiringmanager.class)).thenReturn(identifierObj);
			when(identifierObj.load(1L)).thenReturn(hm);
			Hiringmanager hm1 = new Hiringmanager();
			hm1.setEmail("m@m");
			hm1.setName("mihirrr");
			hm1.setPhone("8369021457");
			hm1.setHmid(1L);
			String s =hmDao.update(1L,1, hm1);
			assertEquals("Hiring Manager Updated Successfully", s);
		}
	  


}
