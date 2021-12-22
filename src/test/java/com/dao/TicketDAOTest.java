package com.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.LoginInfo;
import com.model.TicketsInfo;
import com.util.Connect2SQL;

public class TicketDAOTest {
	static Connection conn;
	static String firstName, lastName, email, pswd, id, emp, employee;
	static int tixNum;
	public static TicketsInfo oneUser;
	public static Set<TicketsInfo> allTix= new HashSet<TicketsInfo>();

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
	public void testall() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getAllTickets();
			assertNotNull(allTix);
		}
	}
	
	@Test
	public void testpending() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getPending("emp1");
			assertNotNull(allTix);
		}
	}
	
	@Test
	public void testclosed() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getClosed("emp2");
			assertNotNull(allTix);
		}
	}
	
	@Test
	public void testallpending() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getAllPending();
			assertNotNull(allTix);
		}
	}
	
	@Test
	public void testallclosed() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getAllClosed();
			assertNotNull(allTix);
		}
	}
	
	@Test
	public void testapprove() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			TicketsDAO.approveTix("50", "admin");
			String s = "ok";
			assertEquals(s, "ok");
		}
	}
	
	@Test
	public void testdeny() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			TicketsDAO.denyTix("50", "admin");
			String s = "ok";
			assertEquals(s, "ok");
		}
	}
	
	@Test
	public void testdelete() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			TicketsDAO.deleteTix("50", "admin");
			String s = "ok";
			assertEquals(s, "ok");
		}
	}

}
