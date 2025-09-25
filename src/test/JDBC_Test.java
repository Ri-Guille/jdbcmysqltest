/**
 * 
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author G 17 sept 2025
 */
public class JDBC_Test {
	
	public static void main(String[] args) {
//		String dbURL = "jdbc:mysql://localhost:3306/test";
//		String username = "root";
//		String password = "admin";
//		DriverManager driverManager;
		Connection connection;
		//creamos una conexion de la base de datos
		try {
			connection = new DBHelper().getConnection();
//						DriverManager.getConnection(dbURL, username, password);
			
			//String dropSQLString = "DROP TABLE users";
			
			createUserTable();
			
			//Pones el nombre y los campos de la tabla que queramos crear
			String createSQL = "create table if not exists users ("
					+"id integer not null,"
					+"name VARCHAR(255),"
					+"age integer not null,"
					+"email VARCHAR(255),"
					+"password VARCHAR(255),"
					+"Vip TINYINT(1),"
					+"PRIMARY KEY (id)"
					+")";
			
				PreparedStatement ps = connection.prepareStatement(createSQL);
				//ps.executeUpdate();
				ps.execute();
				
				//Insert
				
				//String insertSQL = "insert into users values(3," + "'Anuel'" + ", 20, " + "' anuel@gmail.com '" + ", " + "' anu134 '" + ", 0), (2,'Alejandro', 11,' alejandro@gmail.com ', ' alejandro134 ', 0) ";
				//ps = connection.prepareStatement(insertSQL);
				//ps.executeUpdate();
				//ps.execute();
				
				//Update
				
				//String updateSQL = "update users set name = 'Alejandro', email = 'alejandro@gmail.com', password = 'Alejandro123' where id = 2";
				//ps = connection.prepareStatement(updateSQL);
				//ps.executeUpdate();
				//ps.execute();
				
				
				String deleteSQL = "DELETE FROM users WHERE name = 'Anuel'";
				ps = connection.prepareStatement(deleteSQL);
				ps.executeUpdate();
				
				String selecSQL = "SELECT * FROM users";
				ps = connection.prepareStatement(selecSQL);
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					boolean Vip = resultSet.getBoolean("Vip");
					System.out.println("record > id = " + id + " name = " + name + " IsVip = " + Vip);
					}
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}

	/**
	 * 
	 */
	private static void createUserTable() {
		// TODO Auto-generated method stub
		
	}
}	
