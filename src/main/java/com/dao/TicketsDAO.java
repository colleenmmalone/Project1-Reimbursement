package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.driver.MainDriver1;
import com.model.TicketsInfo;

public class TicketsDAO {
	
	static Connection conn;
	static String firstName, lastName, email, pswd, id, emp, employee;
	static int tixNum;
	public final static Logger lg = Logger.getLogger(MainDriver1.class); 

	 
	public TicketsDAO(Connection conn) {//constructor. input is connection to SQL
		this.conn = conn;
	}
	
	//these are for admins
	public static Set<TicketsInfo> getAllTickets() throws SQLException{
		Set<TicketsInfo> allTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		allTickets.add(new TicketsInfo(results.getInt("id"),results.getString("submitted"),results.getString("purchased"),results.getString("cat"),results.getDouble("amt"),results.getString("emp"),results.getString("status"),results.getString("approver")));
	 	}
	 	System.out.println(allTickets);
	 	return allTickets; //return Set
	}
	
	public static Set<TicketsInfo> getAllPending() throws SQLException{
		Set<TicketsInfo> pendingTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets WHERE status='PENDING' ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		pendingTickets.add(new TicketsInfo(results.getInt("id"),results.getString("submitted"),results.getString("purchased"),results.getString("cat"),results.getDouble("amt"),results.getString("emp"),results.getString("status"),results.getString("approver")));
	 	}
	 	return pendingTickets; //return Set
	} 
	
	public static Set<TicketsInfo> getAllClosed() throws SQLException{
		Set<TicketsInfo> closedTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets WHERE status='APPROVED' OR status='DENIED' ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		closedTickets.add(new TicketsInfo(results.getInt("id"),results.getString("submitted"),results.getString("purchased"),results.getString("cat"),results.getDouble("amt"),results.getString("emp"),results.getString("status"),results.getString("approver")));
	 	}
	 	return closedTickets; //return Set
	}

	//these are for employees
	public void addnewTix(String purchased, String category, String amt, String employee) throws SQLException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now(); 
		String nowDate = dtf.format(now);
		
		Double amtD = Double.parseDouble(amt);
		
		PreparedStatement statement = conn.prepareStatement("INSERT INTO tickets (submitted, purchased, cat, amt, emp, status) VALUES (?,?,?,?,?,?)");
		int parameterIndex=0;
		statement.setString(++parameterIndex, nowDate);
		statement.setString(++parameterIndex, purchased);
		statement.setString(++parameterIndex, category);
		statement.setDouble(++parameterIndex, amtD);
		statement.setString(++parameterIndex, employee);
		statement.setString(++parameterIndex, "PENDING");
		statement.executeUpdate();
		lg.info("Ticket was created for "+employee+" claiming $"+amtD+" for "+category);
	}
	
	public static Set<TicketsInfo> getPending(String emp) throws SQLException{
		Set<TicketsInfo> pendingTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets WHERE status='PENDING' AND emp='" + emp+ "' ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		pendingTickets.add(new TicketsInfo(results.getInt("id"),results.getString("submitted"),results.getString("purchased"),results.getString("cat"),results.getDouble("amt"),results.getString("emp"),results.getString("status"),results.getString("approver")));
	 	}
	 	return pendingTickets; //return Set
	}
	
	public static Set<TicketsInfo> getClosed(String emp) throws SQLException{
		Set<TicketsInfo> pendingTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets WHERE status='CLOSED' AND emp='" + emp+ "' ORDER BY emp");
		ResultSet results = statement.executeQuery(); 
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		pendingTickets.add(new TicketsInfo(results.getInt("id"),results.getString("submitted"),results.getString("purchased"),results.getString("cat"),results.getDouble("amt"),results.getString("emp"),results.getString("status"),results.getString("approver")));
	 	}
	 	return pendingTickets; //return Set
	}

	public static void approveTix(String tixNum, String emp) throws Exception {
		PreparedStatement statement = conn.prepareStatement("UPDATE tickets SET approver=?, status='CLOSED' WHERE id=?");
		int parameterIndex=0;
		statement.setString(++parameterIndex, emp);
		statement.setInt(++parameterIndex, Integer.parseInt(tixNum));
		statement.executeUpdate();
		lg.info("Ticket #"+tixNum+" was approved by "+emp);
	}
	
	public static void denyTix(String tixNum, String emp) throws Exception {
		PreparedStatement statement = conn.prepareStatement("UPDATE tickets SET approver=?, status='DENIED' WHERE id=?");
		int parameterIndex=0;
		statement.setString(++parameterIndex, emp);
		statement.setInt(++parameterIndex, Integer.parseInt(tixNum));
		statement.executeUpdate();
		lg.info("Ticket #"+tixNum+" was denied by "+emp);
	}
	
	public static void deleteTix(String tixNum, String emp) throws Exception {
		PreparedStatement statement = conn.prepareStatement("UPDATE tickets SET approver=?, status='DELETED' WHERE id=?");
		int parameterIndex=0;
		statement.setString(++parameterIndex, emp);
		statement.setInt(++parameterIndex, Integer.parseInt(tixNum));
		statement.executeUpdate();
		lg.info("Ticket #"+tixNum+" was deleted by "+emp);
	}



}
