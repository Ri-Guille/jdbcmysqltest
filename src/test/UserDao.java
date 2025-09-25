/**
 * 
 */
package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * data access object for user table
 */
public class UserDao {
	public void insert(int id, String name, Integer age, String email, String password, boolean Vip ) {
		String insertSQL = "insert into users values(3," + "'Anuel'" + ", 20, " + "' anuel@gmail.com '" + ", " + "' anu134 '" + ", 0), (2,'Alejandro', 11,' alejandro@gmail.com ', ' alejandro134 ', 0) ";
		
		Connection connection = DBHelper.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(insertSQL);
			ps.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
