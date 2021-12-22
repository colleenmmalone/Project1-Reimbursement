package com.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.LoginDAO;

public class HibernateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		HibernateUtil h = new HibernateUtil();
	} 

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		List l;
		l = LoginDAO.getAllUsersH();
		assertNotNull(l);
	}

}
