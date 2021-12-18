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
			
			//login or register
			app.get("/login", HelloController.getAllUsersHandler);
			app.get("/login/{email}/{pswd}", HelloController.loginHandler);	
			app.get("register.html/{firstName}/{lastName}/{email}/{pswd}/{birthday}", HelloController.registerHandler);		//should be post	
			app.get("/whoisloggedin", HelloController.whoIsLoggedIn);
			
			//ticket center
			app.get("/pending/{emp}", TicketController.getPendingHandler);
			app.get("/completed/{emp}", TicketController.getCompletedHandler);
			app.get("/admin/pending/", TicketController.getAllPendingHandler);
			app.get("/admin/completed/", TicketController.getAllCompletedHandler);
			app.post("/newTix.html/{purchased}/{cat}/{amt}/{emp}", TicketController.addNewTix);
			
			//change settings
			app.post("/settings/personal/{emp}/{firstName}/{lastName}/{birthday}", HelloController.personalSettingsHandler);
			app.post("/settings/password/{emp}/{password}", HelloController.passwordSettingsHandler);
			
			//logout
			app.get("/logout", HelloController.logoutHandler);
			
			

			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
