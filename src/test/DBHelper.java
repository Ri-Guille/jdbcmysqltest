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
	private static String dbURL = "jdbc:mysql://localhost:3306/test";
	private static String username = "root";
	private static String password = "admin";
	
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(dbURL, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
