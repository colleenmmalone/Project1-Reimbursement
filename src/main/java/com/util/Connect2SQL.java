package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Connect2SQL {
	
	public Connect2SQL() {
		
	}
	
	//public final static Logger lg = Logger.getLogger(Connect2SQL.class.getName());
	public final static Logger lg = Logger.getLogger(Connect2SQL.class);
	
	public static Connection getConnection() throws Exception {
		
			//upload a properties file and connect to SWL
			FileInputStream propInput = new FileInputStream("/Users/briarrose/Dropbox/Revature/Project1/application.properties"); 
			Properties props = new Properties();
			props.load(propInput); 
			
			String url = (String) props.getProperty("URL");
			String username = (String) props.getProperty("username");
			String password = (String) props.getProperty("password");
			
			return DriverManager.getConnection(url, username, password); //return a connection
	}
	
}	