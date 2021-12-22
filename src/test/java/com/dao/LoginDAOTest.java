package com.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.LoginInfo;
import com.util.Connect2SQL;

public class LoginDAOTest {
	
	static Connection conn;
	static String firstName, lastName, email, pswd, id;
	public static LoginInfo oneUser;
	public static Set<LoginInfo> allUsers = new HashSet<LoginInfo>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		oneUser = new LoginInfo();
		allUsers = new HashSet<LoginInfo>();
	}

	@After
	public void tearDown() throws Exception {
		oneUser = new LoginInfo();
		allUsers = new HashSet<LoginInfo>();
	}

	@Test
	public void test() throws Exception {
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			assertNotNull(conn);
		}	
	}
	
	@Test
	public void testgetallusers() throws SQLException, Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			allUsers = LoginDAO.getAllUsers();
			assertNotNull(allUsers);
		}	
	}
	
	@Test
	public void testgetoneusers() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			oneUser = LoginDAO.getOneUser("emp1");
			assertNotNull(oneUser);
		}	
	}
	
	@Test
	public void testlogin() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			oneUser = LoginDAO.login("emp1", "emp1");
			assertNotNull(oneUser);
		}	
	}
	
	@Test
	public void testloginFalse() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			oneUser = LoginDAO.login("emp1", "cake");
			assertNotNull(oneUser);
		}	
	}
	
	@Test
	public void testregisterFalse() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			oneUser = LoginDAO.addNewUser("emp1","emp1","emp1","emp1","emp1");
			assertNotNull(oneUser);
		}	
	}
	
	@Test
	public void testreturnuser() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			LoginDAO.oneUser = new LoginInfo();
			oneUser = LoginDAO.getCurrentUser();
			assertNotNull(oneUser);
		}	
	}
	
	@Test
	public void testnulluser() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			LoginDAO.setNullUser();
			assertEquals(LoginDAO.oneUser.getFirst_name(), "null");
		}	
	}
	
	
	@Test
	public void testoneuserH() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			LoginDAO.oneUser = new LoginInfo();
			List l = LoginDAO.getOneUserH("emp1");
			assertNotNull(l);
		}	
	}
	
	@Test
	public void testalluserH() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			LoginDAO.oneUser = new LoginInfo();
			List l = LoginDAO.getAllUsersH();
			assertNotNull(l);
		}	
	}
	
	@Test
	public void testupdatepass() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			logindao.updatePasswordSettings("emp1", "emp1");
			assertEquals(LoginDAO.oneUser.getPswd(), "emp1");
		}	
	}
	
	@Test
	public void testupdatepers() throws Exception{
		try(Connection conn = Connect2SQL.getConnection()){
			LoginDAO logindao = new LoginDAO(conn);
			logindao.updatePersonalSettings("emp4", "emp4", "emp4", "2000-04-30");
			assertEquals(LoginDAO.oneUser.getFirst_name(), "emp4");
		}	
	}
}
