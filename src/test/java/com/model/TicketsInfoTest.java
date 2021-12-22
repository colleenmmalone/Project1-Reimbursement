package com.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TicketsInfoTest {
	static TicketsInfo l, n;



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		l = new TicketsInfo(1, "first", "last", "em", 12.34, "pswd", "id", "bday");
		n = new TicketsInfo();		
	}

	@Test
	public void testFirst() {
		l.setTixNum(6);
		assertEquals(l.getTixNum(), 6);
	}
	
	@Test
	public void testLast() {
		l.setSubmitted("bye");
		assertEquals(l.getSubmitted(), "bye");
	}
	
	@Test
	public void testEmail() {
		l.setPurchased("e2");
		assertEquals(l.getPurchased(), "e2");
	}
	
	@Test
	public void testPswd() {
		l.setCategory("cat");
		assertEquals(l.getCategory(), "cat");
	}
	
	@Test
	public void testBday() {
		l.setAmt(3.14);
		assertNotNull(l.getAmt());
	}
	
	@Test
	public void testId() {
		l.setStatus("string");
		assertEquals(l.getStatus(), "string");
	}
	
	@Test
	public void testApprover() {
		l.setApprover("jelly");
		assertEquals(l.getApprover(), "jelly");
	}
	
	@Test
	public void testEmp() {
		l.setEmp("Manny");
		assertEquals(l.getEmp(), "Manny");
	}
	
	@Test
	public void toStringTest() {
		assertNotNull(l.toString());
	}
	
	@Test
	public void toStringTestP() {
		n.setStatus("PENDING");
		assertNotNull(n.toString());
	}


}
