package com.driver;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.controller.HelloController;
import com.controller.TicketController;
import com.dao.LoginDAO;
import com.dao.TicketsDAO;
import com.util.Connect2SQL;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class MainDriver1 {
	static Connection conn;
	public final static Logger lg = Logger.getLogger(MainDriver1.class); 
	static Javalin app;
	
	public static void main(String [] args){
		
		try(Connection conn = Connect2SQL.getConnection()){ //connect to DB	
			lg.info("Connection to Database was established");
			LoginDAO logindao = new LoginDAO(conn);
			TicketsDAO ticketsdao = new TicketsDAO(conn);
						
			app = Javalin.create(ctx->{ //make Javalin app from Jas's demo
				ctx.enableCorsForAllOrigins();
				ctx.addStaticFiles("HTML", Location.CLASSPATH);
			}).start(7001);
			
			app.get("/login", HelloController.getAllUsersHandler);
			app.get("/login/{email}/{pswd}", HelloController.loginHandler);	
			app.get("register.html/{firstName}/{lastName}/{email}/{pswd}/{birthday}", HelloController.registerHandler);
			app.get("/whoisloggedin", HelloController.whoIsLoggedIn);
		//	app.get("/ticketCenter.html", HelloController.getOneUserHandler);
			app.get("/pending/{emp}", TicketController.getPendingHandler);
		//	app.get("/ticketCenter.html/closed", TicketController.getClosedHandler);
			app.post("newTix.html/{purchased}/{cat}/{amt}/{emp}", TicketController.addNewTix);
		//	app.post("ticketCenter.html/{tixNum}", TicketController.deleteTix);
		//	app.post("ticketCenter.html/{tixNum}/{approver}/{stat}", TicketController.approveTix);
			
//			Set<TicketsInfo> allTix = ticketsdao.getAllTickets();
//			System.out.println(allTix);
			
		
			

			
		//	ticketsdao.addnewTix("2021-03-04", "GAS", "91.32", "emp2");
			
			 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
