package utility;

import java.sql.*;

import driver.Base;

public class OracleJDBC extends Base {
	
	
public static Connection DatabaseConn(){				
	try{	
		Connection con= null;	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection(CONFIG.getProperty("connstring"),CONFIG.getProperty("dbuser"),CONFIG.getProperty("dbpwd"));
			return con;
		}catch(Exception e){
			System.out.println(e);
			return null;
			}		
	}	
	

}
