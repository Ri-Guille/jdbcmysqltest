/**
 * 
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * data access object for user table
 */
public class UserDao {
	public void insert(int id, String name, Integer age, String email, String password, boolean Vip ) {
		String insertSQL = "insert into users values(?, ?, ?, ?, ?, ?)";
		
		Connection connection = DBHelper.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(insertSQL);
			/**
			 * La instancia/objeto de la clase PreparedStatement se encarga de sustituir los placeholders ? con los valores pasados
			 * a traves de los parametros, de manera que nos permite reutilizar el codigo aun más.
			 */
			ps.setInt(1, id);//Sustituye el primer placeholder con el valor de id
			ps.setString(2, name);//Sustituye el segundo placeholder con el valor de name
			ps.setInt(3, age);//Sustituye el tercer placeholder con el valor  de age
			ps.setString(4, email);//Sustituye el cuerto placeholder con el valor de email
			ps.setString(5, password);//Sustituye el quinto placeholder con el valor de password
			ps.setBoolean(6, Vip);//Sustituye el sexto placeholder con el vaor de Vip
			ps.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
