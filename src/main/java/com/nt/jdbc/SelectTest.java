package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
/**
 * @author Phanidhar
 *
 */

public class SelectTest {
private static final String  GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD FROM STUDENT";
//create Logger
private static Logger  logger=Logger.getLogger(com.nt.jdbc.SelectTest.class);
	public static void main(String[] args) {
		SimpleLayout layout=null;
		ConsoleAppender appender=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		//create Layout
		layout=new SimpleLayout();
		//create Appender
		appender=new ConsoleAppender(layout);
		//add Appender
		logger.addAppender(appender);
		//specify logger level to retrive log messages
		logger.setLevel(Level.ALL);
		logger.info("Log4jApp1.mod1.SelectTest.main(-).Application Started");
		
		try{
			//register drriver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.debug("Log4jApp1.mod1.SelectTest.main(-).jdbc driver registered");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			logger.debug("Log4jApp1.mod1.SelectTest.main(-).connection established");
			//create Statement obj
			if(con!=null){
				st=con.createStatement();
				logger.warn("Log4jApp1.mod1.SelectTest.main(-).Statement created(Simple Statement used but prefer using PreparedStatement)");
			}
			//send and execute SQL Query in DB s/w
			if(st!=null){
				rs=st.executeQuery(GET_STUDENTS_QUERY);
				logger.debug("Log4jApp1.mod1.SelectTest.main(-).ResultSet obj created");
			}
			//process the Reusltset
			if(rs!=null){
				while(rs.next()){
					 flag=true;
					 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
				}//while
				logger.debug("Log4jApp1.mod1.SelectTest.main(-).ResultSet obj is processed");
			}//if
			if(flag==false){
				System.out.println("ResultSEt obj/Db table empty");
				logger.info("Log4jApp1.mod1.SelectTest.main(-).Db table Emty"+new Date());
			}
		}//try
		catch(SQLException se){
			logger.error("Log4jApp1.mod1.SelectTest.main(-).DB Problem:"+se.getErrorCode());
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			logger.error("Log4jApp1.mod1.SelectTest.main(-).JDBC Driver Problem:"+cnf.getMessage());
			cnf.printStackTrace();
		}
		catch(Exception e){
			logger.fatal("Log4jApp1.mod1.SelectTest.main(-).UnKnow Problem:"+e.getMessage());
			e.printStackTrace();
		}
	finally{
		 logger.debug("Log4jApp1.mod1.SelectTest.main(-).finally block");
		 try{
		   if(rs!=null){
			   rs.close();
			   logger.debug("Log4jApp1.mod1.SelectTest.main(-).finally block:ResultSet closed");
		   }//if
		 }//try
		   catch(SQLException se){
			   logger.error("Log4jApp1.mod1.SelectTest.main(-).finally block:ResultSet not closed");
			   se.printStackTrace();
		   }
		 try{
			   if(st!=null){
				   st.close();
				   logger.debug("Log4jApp1.mod1.SelectTest.main(-).finally block:Statememt closed");
			   }//if
			 }//try
		   catch(SQLException se){
				   logger.error("Log4jApp1.mod1.SelectTest.main(-).finally block:Statement not closed");
				   se.printStackTrace();
		   }
		 try{
			   if(con!=null){
				   con.close();
				   logger.debug("Log4jApp1.mod1.SelectTest.main(-).finally block:Connection closed");
			   }//if
			 }//try
		   catch(SQLException se){
				   logger.error("Log4jApp1.mod1.SelectTest.main(-).finally block:Connection not closed");
				   se.printStackTrace();
		   }
	}//finally
		logger.info("Log4jApp1.mod1.SelectTest.main(-).Application exited");
	}//main
}//class
