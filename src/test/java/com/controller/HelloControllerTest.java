package com.controller;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.LoginDAO;
import com.dao.TicketsDAO;
import com.driver.MainDriver1;
import com.util.Connect2SQL;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class HelloControllerTest {
	public final static Logger lg = Logger.getLogger(MainDriver1.class); 
	static Javalin app;
	MainDriver1 md;
	


	@Test
	public void test() throws Exception {
		md = new MainDriver1();
		assertNotNull(md);
	}

}
