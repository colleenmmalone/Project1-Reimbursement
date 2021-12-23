package com.controller;



import java.sql.Connection;
import java.util.Set;

import com.dao.TicketsDAO;
import com.model.TicketsInfo;
import com.util.Connect2SQL;

import io.javalin.http.Handler;

public class TicketController {
	
	static Connection conn;
	static Set<TicketsInfo> allTix;
	static TicketsInfo tix;
 
	public static Handler addNewTix = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			ticketsdao.addnewTix(ctx.pathParam("purchased"),ctx.pathParam("cat"),ctx.pathParam("amt"),ctx.pathParam("emp"));						
		}		
	};
	
	public static Handler getPendingHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getPending(ctx.pathParam("emp"));			
			ctx.json(allTix);
		}		
	};
	
	public static Handler getCompletedHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getClosed(ctx.pathParam("emp"));			
			ctx.json(allTix);
		}		
	};
	
	public static Handler getAllPendingHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getAllPending();			
			ctx.json(allTix);
		}		
	};
	
	public static Handler getAllCompletedHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			allTix = TicketsDAO.getAllClosed();			
			ctx.json(allTix);
		}		
	};
	
	public static Handler approveTixHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			TicketsDAO.approveTix(ctx.pathParam("tixNum"),ctx.pathParam("emp"));			
			ctx.result("performed successfully");
		}		
	};
	
	public static Handler denyTixHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			TicketsDAO.denyTix(ctx.pathParam("tixNum"),ctx.pathParam("emp"));	
			ctx.result("performed successfully");
		}		
	};
	
	public static Handler deleteTixHandler = ctx->{
		try(Connection conn = Connect2SQL.getConnection()){ 
			TicketsDAO ticketsdao = new TicketsDAO(conn);
			TicketsDAO.deleteTix(ctx.pathParam("tixNum"),ctx.pathParam("emp"));		
			ctx.result("performed successfully");
		}		
	};
	
	



}
