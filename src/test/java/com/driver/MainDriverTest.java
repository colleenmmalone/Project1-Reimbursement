package com.driver;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.javalin.Javalin;

public class MainDriverTest {
	static Connection conn;
	public final static Logger lg = Logger.getLogger(MainDriver1.class); 
	static Javalin app;
	static String s;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s = "Hello";
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
	public void test() {
		System.out.println(s);
		assertEquals(s, "Hello");
	}

}
