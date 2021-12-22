package com.util;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import testSuite.AllTests;

public class ConnectTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void connTest2() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			assertNotNull(conn);	
		}
	}
	
	@Test
	public void connTestConst() throws Exception {
		Connect2SQL c = new Connect2SQL();
		//assert(c != null);
		assertNotNull(c);
	}
	
	@Test
	public void allTestsTest(){
		AllTests at = new AllTests();
	}

}
