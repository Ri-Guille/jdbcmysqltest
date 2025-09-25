/**
 * 
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author G 24 sept 2025
 */
public class DBHelper {
	String dbURL = "jdbc:mysql://localhost:3306/test";
	String username = "root";
	String password = "admin";
	Connection getConnection() {
		try {
			return DriverManager.getConnection(dbURL, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
