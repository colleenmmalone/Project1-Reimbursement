package com.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.LoginDAO;
import com.model.LoginInfo;
import com.util.Connect2SQL;
import com.util.HibernateUtil;


import io.javalin.http.Handler;

public class HelloController {
	static Connection conn;
	static Set<LoginInfo> allUsers;
	static LoginInfo emp;
	static Session session;
	static Transaction transaction;
	 
	public static Handler loginHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			LoginDAO logindao = new LoginDAO(conn);
			LoginInfo emp = new LoginInfo();
			emp = LoginDAO.login(ctx.pathParam("email"), ctx.pathParam("pswd"));
			ctx.json(emp);
		}
	};
	
	public static Handler whoIsLoggedIn = ctx->{
			LoginInfo emp = LoginDAO.getCurrentUser();
			System.out.println(emp);
			ctx.json(emp);
	};
	
	public static Handler getAllUsersHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
		LoginDAO logindao = new LoginDAO(conn);
		allUsers = LoginDAO.getAllUsers();	
		ctx.json(allUsers);
		}	
	};

	public static Handler registerHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			LoginDAO logindao = new LoginDAO(conn);
			LoginDAO.addNewUser(ctx.pathParam("firstName"),ctx.pathParam("lastName"),ctx.pathParam("email"), ctx.pathParam("pswd"),ctx.pathParam("birthday"));			
			LoginInfo emp = null;
			emp = LoginDAO.getOneUser(ctx.pathParam("email"));
			if(emp==null) {
				System.out.println("Account was not created");
				ctx.result("Failure!");
			}else {
				System.out.println("Account was created!!");
				ctx.result("Success!");
			}
		}
	};
	

	public static Handler logoutHandler = ctx->{
			LoginDAO.setNullUser();			
			ctx.redirect("index.html");			
	};
	
	public static Handler personalSettingsHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			LoginDAO logindao = new LoginDAO(conn);
			logindao.updatePersonalSettings(ctx.pathParam("emp"), ctx.pathParam("firstName"),ctx.pathParam("lastName"),ctx.pathParam("birthday"));		
		}
		ctx.result("name and/or birthday updated");
	};
	
	public static Handler passwordSettingsHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			LoginDAO logindao = new LoginDAO(conn);
			logindao.updatePasswordSettings(ctx.pathParam("emp"), ctx.pathParam("password"));				
		}	
		ctx.result("password updated");
	};

	public static Handler HibernateDemo = ctx -> {
		ArrayList<LoginInfo> l = new ArrayList<>();
        List results = LoginDAO.getAllUsersH();
        ctx.json(results);
	};

}
