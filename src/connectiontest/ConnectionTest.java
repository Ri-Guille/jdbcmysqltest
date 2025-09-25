package connectiontest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException, InterruptedException {
		String dbURL = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "admin";
		DriverManager driverManager;
		//creamos la conexion con la base de datos
		Connection connection = DriverManager.getConnection(dbURL, username, password);
		System.out.println(connection);
		//al mandar demasidadas conexciones se consumen muchos recursos por lo tanto el sistema nos manda un error
		//informando que las conexiones han sido rechazadas.
		Connection[] connections = new Connection[1000];
		for (int i = 0; i < 100; i++) {
			connections[i] = DriverManager.getConnection(dbURL, username, password);
			System.out.println(connections);
			
		}
		
		Thread.currentThread().wait(1232334);
	}	
}
	