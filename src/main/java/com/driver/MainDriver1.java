package com.driver;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.controller.HelloController;
import com.controller.TicketController;
import com.dao.LoginDAO;
import com.dao.TicketsDAO;
import com.model.LoginInfo;
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
			app.get("/pending/{emp}", TicketController.getPendingHandler);
			app.get("/completed/{emp}", TicketController.getCompletedHandler);
			app.post("/newTix.html/{purchased}/{cat}/{amt}/{emp}", TicketController.addNewTix);
			app.get("/logout", HelloController.logoutHandler);
			app.post("/settings/personal/{emp}/{firstName}/{lastName}/{birthday}", HelloController.personalSettingsHandler);
			//app.post("/settings/email/{emp}/{newEmail}", HelloController.emailSettingsHandler);
			app.post("/settings/password/{emp}/{password}", HelloController.passwordSettingsHandler);
			
			
		//	app.post("ticketCenter.html/{tixNum}", TicketController.deleteTix);
		//	app.post("ticketCenter.html/{tixNum}/{approver}/{stat}", TicketController.approveTix);
			
//			Set<TicketsInfo> allTix = ticketsdao.getAllTickets();
//			System.out.println(allTix);
//			LoginDAO.oneUser = new LoginInfo("asf","asf","asf","asf","asf");
//			logindao.updatePersonalSettings("admin", "Emma", "Watson", "1978-02-14");
//			logindao.updatePasswordSettings("admin", "admin");
//			System.out.println(LoginDAO.oneUser);
			

			
		//	ticketsdao.addnewTix("2021-03-04", "GAS", "91.32", "emp2");
			
			 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
