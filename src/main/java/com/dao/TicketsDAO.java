package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import com.model.TicketsInfo;

public class TicketsDAO {
	
	static Connection conn;
	static String firstName, lastName, email, pswd, id;

	
	public TicketsDAO(Connection conn) {//constructor. input is connection to SQL
		this.conn = conn;
	}
	
	//these are for admins
	public static Set<TicketsInfo> getAllTickets() throws SQLException{
		Set<TicketsInfo> allTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		allTickets.add(new TicketsInfo(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getDouble(5),results.getString(6),results.getString(7),results.getString(8)));
	 	}
	 	System.out.println(allTickets);
	 	return allTickets; //return Set
	}
	
	public static Set<TicketsInfo> getAllPending() throws SQLException{
		Set<TicketsInfo> pendingTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets WHERE status='PENDING' ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		pendingTickets.add(new TicketsInfo(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getDouble(5),results.getString(6),results.getString(7),results.getString(8)));
	 	}
	 	return pendingTickets; //return Set
	}
	
	public static Set<TicketsInfo> getAllClosed() throws SQLException{
		Set<TicketsInfo> closedTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets WHERE status='CLOSED' ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		closedTickets.add(new TicketsInfo(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getDouble(5),results.getString(6),results.getString(7),results.getString(8)));
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
	}
	
	public static Set<TicketsInfo> getPending(String emp) throws SQLException{
		Set<TicketsInfo> pendingTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets WHERE status='PENDING' AND emp='" + emp+ "' ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		pendingTickets.add(new TicketsInfo(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getDouble(5),results.getString(6),results.getString(7),results.getString(8)));
	 	}
	 	return pendingTickets; //return Set
	}
	
	public static Set<TicketsInfo> getClosed(String emp) throws SQLException{
		Set<TicketsInfo> pendingTickets = new HashSet<TicketsInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM tickets WHERE status='CLOSED' AND emp='" + emp+ "' ORDER BY submitted");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		pendingTickets.add(new TicketsInfo(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getDouble(5),results.getString(6),results.getString(7),results.getString(8)));
	 	}
	 	return pendingTickets; //return Set
	}

}
