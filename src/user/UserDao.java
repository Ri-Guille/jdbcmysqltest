/**
 * 
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;

/**
 * data access object for user table
 */
public class UserDao implements UserDaoInterface {
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
			ps.close();
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

		Connection connection = DBHelper.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(deleteSQL);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			System.out.println("Delete rows " + result);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(int id, String email) {
		String updateSQL = "update users set email = ? where id = ?";
		try (Connection connection = DBHelper.getConnection();
				PreparedStatement ps = connection.prepareStatement(updateSQL);) {
			ps.setString(1, email);
			ps.setInt(2, id);

			int result = ps.executeUpdate();
			System.out.println("we have updates " + result + "rows.");
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<User> findAll() {

		String selecSQL = "SELECT * FROM users where name = 'Alejandro' ";
		User[] users;
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(null);
		Connection connection = DBHelper.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(selecSQL);
			ResultSet resultSet = ps.executeQuery();
//			System.out.println("Total rows is " + resultSet.last());
//			System.out.println("rows = " + resultSet.getFetchSize());
			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				boolean Vip = resultSet.getBoolean("Vip");
				float balance = resultSet.getFloat("balance");
				System.out.println("record > id = " + id + " name = " + name + " IsVip = " + Vip);
				userList.add(new User(name, age, email, password, id, Vip, balance));
			}

			return userList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public User find(int id) {

		String selecSQL = "SELECT * FROM users where id = ?";

		try (Connection connection = DBHelper.getConnection();
				PreparedStatement ps = connection.prepareStatement(selecSQL);) {
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
//			System.out.println("Total rows is " + resultSet.last());
//			System.out.println("rows = " + resultSet.getFetchSize());
			if (resultSet.next()) {

				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				boolean Vip = resultSet.getBoolean("Vip");
				float balance = resultSet.getFloat("balance");
				System.out.println("record > id = " + id + " name = " + name + " IsVip = " + Vip);
				resultSet.close();
				return new User(name, age, email, password, id, Vip, balance);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public int insertAll(User[] users) {
		String insertSQL = "insert into users values(?, ?, ?, ?, ?, ?, ?)";
		Connection connection = DBHelper.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(insertSQL);
			for (int i = 0; i < users.length; i++) {
				/**
				 * La instancia/objeto de la clase PreparedStatement se encarga de sustituir los
				 * placeholders ? con los valores pasados a traves de los parametros, de manera
				 * que nos permite reutilizar el codigo aun más.
				 */
				ps.setLong(1, users[i].getId());// Sustituye el primer placeholder con el valor de id
				ps.setString(2, users[i].getName());// Sustituye el segundo placeholder con el valor de name
				ps.setInt(3, users[i].getAge());// Sustituye el tercer placeholder con el valor de age
				ps.setString(4, users[i].getEmail());// Sustituye el cuerto placeholder con el valor de email
				ps.setString(5, users[i].getPassword());// Sustituye el quinto placeholder con el valor de password
				ps.setBoolean(6, users[i].isVip());
				ps.setFloat(7, users[i].getBalance());
				ps.addBatch();
			}
			return ps.executeBatch().length;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boolean transfer(long fromUserId, long toUserId, float amount) {
		if (amount < 0) {
			System.out.println("the amount can not be negative");
			return false;
		}
		String updateSQL1 = "update users set balance = balance - " + amount + "where id = " + fromUserId
				+ " and balance >= " + amount;
		String updateSQL2 = "update users set balance = balance + " + amount + "where id = " + toUserId;

		try (Connection connection = DBHelper.getConnection();) {
			PreparedStatement ps = connection.prepareStatement(updateSQL1);
			int rowsUpdate = ps.executeUpdate();
			System.out.println("rows updates = " + rowsUpdate);
			if(rowsUpdate > 0) {
				ps = connection.prepareStatement(updateSQL2);
				rowsUpdate = ps.executeUpdate();
				System.out.println("rows updates = " + rowsUpdate);
			}
	} 
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean transferWithBatch(long fromUserId, long toUserId, float amount) {
		String updateSQL1 = "update users set balance = balance - " + amount + "where id = " + fromUserId
				+ " and balance >= " + amount;
		String updateSQL2 = "update users set balance = balance + " + amount + "where id = " + toUserId;

		try (Connection connection = DBHelper.getConnection(); Statement st = connection.createStatement();) {

			st.addBatch(updateSQL1);
			st.addBatch(updateSQL2);
			st.executeBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
