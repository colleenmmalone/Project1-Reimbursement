package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.driver.MainDriver1;
import com.model.LoginInfo;
import com.util.HibernateUtil;

public class LoginDAO {
	static Connection conn;
	static String firstName, lastName, email, pswd, id;
	public static LoginInfo oneUser;
	static Session session;
	static Transaction transaction;
	public final static Logger lg = Logger.getLogger(MainDriver1.class); 
 
	 
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
			lg.info(email+" has logged in.");
			return oneUser; //return Set	 	
		}else {
			oneUser = new LoginInfo("null","null","null","null","null","null");
			lg.warn("Login failed!");
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
			lg.info("A new account was created for "+firstName+" "+lastName+" with email "+email);
			
		}else {
			lg.info("Cannot register; a user with this email address already exists.");
			oneUser = new LoginInfo("null","null","null","null","null","null");
		}
		return oneUser;
	}
	
	public static LoginInfo getCurrentUser() {
		return oneUser;
	}

	public static void setNullUser() {
		oneUser = new LoginInfo("null","null","null","null","null","null");
		System.out.println(oneUser);
	}

	public void updatePersonalSettings(String emp, String firstName, String lastName, String birthday) throws Exception{
		PreparedStatement statement = conn.prepareStatement("UPDATE logins SET birthday=?, first_name=?, last_name=? WHERE email=?");
		int parameterIndex=0;
		statement.setString(++parameterIndex, birthday);
		statement.setString(++parameterIndex, firstName);
		statement.setString(++parameterIndex, lastName);
		statement.setString(++parameterIndex, emp);
		statement.executeUpdate();	
		oneUser.setBirthday(birthday);
		oneUser.setFirst_name(firstName);
		oneUser.setLast_name(lastName);
		lg.info("personal settings were changed");
		
	}

	public void updatePasswordSettings(String emp, String pswd) throws Exception {
		PreparedStatement statement = conn.prepareStatement("UPDATE logins SET pswd=? WHERE email=?");
		int parameterIndex=0;
		statement.setString(++parameterIndex, pswd);
		statement.setString(++parameterIndex, emp);
		statement.executeUpdate();	
		oneUser.setPswd(pswd);
		lg.info("password settings were changed");		
	}

	public static List getAllUsersH() throws Exception{
        List results = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();;
            transaction = session.beginTransaction();
            String hql = "FROM logins";
            Query query = session.createQuery(hql);
            results = query.list();
            System.out.println(results);      
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return results;
	};
	
	
	public static List getOneUserH(String email) throws Exception{
        List results = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();;
            transaction = session.beginTransaction();
            String hql = "FROM logins WHERE email = '"+email+"'" ;
            Query query = session.createQuery(hql);
            results = query.list();
            System.out.println(results);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return results;
	}

}
