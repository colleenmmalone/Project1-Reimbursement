package com.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginInfoTest {
	static LoginInfo l, n;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		l = new LoginInfo("first", "last", "em", "pswd", "id", "bday");
		n = new LoginInfo();		
	}
	
	@Test
	public void testFirst() {
		l.setFirst_name("hi");
		assertEquals(l.getFirst_name(), "hi");
	}
	
	@Test
	public void testLast() {
		l.setLast_name("bye");
		assertEquals(l.getLast_name(), "bye");
	}
	
	@Test
	public void testEmail() {
		l.setEmail("e2");
		assertEquals(l.getEmail(), "e2");
	}
	
	@Test
	public void testPswd() {
		l.setPswd("cat");
		assertEquals(l.getPswd(), "cat");
	}
	
	@Test
	public void testBday() {
		l.setBirthday("cake");
		assertEquals(l.getBirthday(), "cake");
	}
	
	@Test
	public void testId() {
		l.setId("string");
		assertEquals(l.getId(), "string");
	}
	
	@Test
	public void toStringTest() {
		assertNotNull(l.toString());
	}

}
