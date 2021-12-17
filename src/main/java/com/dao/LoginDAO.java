package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.model.LoginInfo;

public class LoginDAO {
	static Connection conn;
	static String firstName, lastName, email, pswd, id;
	public static LoginInfo oneUser;

	
	public LoginDAO(Connection conn) {//constructor. input is connection to SQL
		this.conn = conn;
	}
	
	public static Set<LoginInfo> getAllUsers() throws SQLException{
		Set<LoginInfo> allUsers = new HashSet<LoginInfo>();
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM logins");
		ResultSet results = statement.executeQuery();
		
	 	while(results.next()) { //store retrieved data into LoginInfo set
	 		//add to Set
	 		allUsers.add(new LoginInfo(results.getString("first_name"),results.getString("last_name"),results.getString("email"),results.getString("pswd"),results.getString("id"),results.getString("birthday")));
	 	}
	 	return allUsers; //return Set
	}
	
	public static LoginInfo getOneUser(String email) throws SQLException{
		oneUser = null;
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM logins WHERE email = '"+email+"'");
		ResultSet results = statement.executeQuery();
		while(results.next()) { //store retrieved data into LoginInfo set
		 	oneUser = new LoginInfo(results.getString("first_name"),results.getString("last_name"),results.getString("email"),results.getString("pswd"),results.getString("id"),results.getString("birthday"));
		 }
	 	return oneUser; //return Set	 	
	}
	
	public static LoginInfo login(String email, String pswd) throws SQLException{
		oneUser = null;
		oneUser = getOneUser(email);
		if(pswd.equals(oneUser.getPswd())) {
			return oneUser; //return Set	 	
		}else {
			oneUser = new LoginInfo("null","null","null","null","null");
			return oneUser;
		}
	}
	
	public static LoginInfo addNewUser(String firstName, String lastName, String email, String pswd, String birthday) throws Exception{
		oneUser = null;
		oneUser = getOneUser(email);
		System.out.println(oneUser);
		if(oneUser==null) {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO logins (first_name, last_name, email, pswd, birthday, id) VALUES (?,?,?,?,?,?)");
			int parameterIndex=0;
			statement.setString(++parameterIndex, firstName);
			statement.setString(++parameterIndex, lastName);
			statement.setString(++parameterIndex, email);
			statement.setString(++parameterIndex, pswd);
			statement.setString(++parameterIndex, birthday);
			statement.setString(++parameterIndex, "EMPLOYEE");
			statement.executeUpdate();	
			System.out.println("An account was made for "+firstName+" "+lastName+" with email "+email+".");
			oneUser = new LoginInfo(firstName, lastName, email, pswd, "EMPLOYEE", birthday);
		}else {
			System.out.println("A user with this email address already exists. Please login");
			oneUser = new LoginInfo("null","null","null","null","null");
		}
		return oneUser;
	}
	
	public static LoginInfo getterOneUser() {
		return oneUser;
	}

	public static void setNullUser() {
		oneUser = new LoginInfo("null","null","null","null","null");
		System.out.println(oneUser);
	}

}
