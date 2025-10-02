/**
 * 
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

/**
 * data access object for user table
 */
public class UserDao {
	public void insert(int id, String name, Integer age, String email, String password, boolean Vip) {
		String insertSQL = "insert into users values(?, ?, ?, ?, ?, ?)";

		Connection connection = DBHelper.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(insertSQL);
			/**
			 * La instancia/objeto de la clase PreparedStatement se encarga de sustituir los
			 * placeholders ? con los valores pasados a traves de los parametros, de manera
			 * que nos permite reutilizar el codigo aun más.
			 */
			ps.setInt(1, id);// Sustituye el primer placeholder con el valor de id
			ps.setString(2, name);// Sustituye el segundo placeholder con el valor de name
			ps.setInt(3, age);// Sustituye el tercer placeholder con el valor de age
			ps.setString(4, email);// Sustituye el cuerto placeholder con el valor de email
			ps.setString(5, password);// Sustituye el quinto placeholder con el valor de password
			ps.setBoolean(6, Vip);// Sustituye el sexto placeholder con el vaor de Vip
			// Si nos devuelve 0 significa que no a cambiado ninguna fila en la base de
			// datos
			int result = ps.executeUpdate();
			System.out.println("Insert rows " + result);
			// Close the conecction with the database
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Tenemos que determinar que input o entradas tenemos que proporcionar a este
	 * metodo, y que es lo que nos debe devolver como resultado
	 */
	public void delete(int id) {
		String deleteSQL = "DELETE FROM users WHERE id = ?";

		Connection conn = DBHelper.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(deleteSQL);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			System.out.println("Delete rows " + result);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(int id, String email) {

	}

	public User[] findAll() {

		return null;

	}

	public UserDao find(int id) {

		return null;

	}
}
