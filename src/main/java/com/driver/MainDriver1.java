package com.driver;
 
import java.sql.Connection;
import java.sql.SQLException;

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
	 
	public static void main(String [] args) throws Exception{
		 
		try(Connection conn = Connect2SQL.getConnection()){ //connect to DB	
			lg.info("Connection to Database was established");
			LoginDAO logindao = new LoginDAO(conn);
			TicketsDAO ticketsdao = new TicketsDAO(conn);
						
			app = Javalin.create(ctx->{ //make Javalin app from Jas's demo
				ctx.enableCorsForAllOrigins();
				ctx.addStaticFiles("HTML", Location.CLASSPATH);
			}).start(7001);
			 
			lg.info("App has started on port 7001");
			//login or register
			app.get("/login", HelloController.getAllUsersHandler);
			app.get("/login/{email}/{pswd}", HelloController.loginHandler);	
			app.get("register.html/{firstName}/{lastName}/{email}/{pswd}/{birthday}", HelloController.registerHandler);		//should be post	
			app.get("/whoisloggedin", HelloController.whoIsLoggedIn);
			
			//ticket center
			app.get("/pending/{emp}", TicketController.getPendingHandler);
			app.get("/completed/{emp}", TicketController.getCompletedHandler);
			app.post("/newTix.html/{purchased}/{cat}/{amt}/{emp}", TicketController.addNewTix);
			app.post("/delete/{emp}/{tixNum}", TicketController.deleteTixHandler);
			
			//admin ticket center
			app.get("/admin/pending/", TicketController.getAllPendingHandler);
			app.get("/admin/completed/", TicketController.getAllCompletedHandler);
			app.post("/approve/{emp}/{tixNum}", TicketController.approveTixHandler);
			app.post("/deny/{emp}/{tixNum}", TicketController.denyTixHandler);
			
			//change settings
			app.post("/settings/personal/{emp}/{firstName}/{lastName}/{birthday}", HelloController.personalSettingsHandler);
			app.post("/settings/password/{emp}/{password}", HelloController.passwordSettingsHandler);
			
			//logout
			app.get("/logout", HelloController.logoutHandler);
			
			//hibernate
			app.get("/hbdemo", HelloController.HibernateDemo);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		LoginDAO.getAllUsersH();
//		LoginDAO.getOneUserH("emp1");
		
	}
	
	
	public MainDriver1() {
		
	}
	
	
	
	
}
