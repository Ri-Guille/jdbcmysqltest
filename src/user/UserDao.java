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
			 * que nos permite reutilizar el codigo aun m�s.
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
				 * placeholders ? con los valores pasados a traves de los parametros de manera
				 * que nos permite reutilizar el codigo aun mas.
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

    @Override
    public boolean transferWithQueryAndUpdate(long fromUserId, long toUserId, float amount) {
        //Paso 1:consulta los datos de los usuarios para chequear si existen ellos y cumplen las condiciones
        //Paso 2: ejecutamos los sqls de update
            String selecSQL = "SELECT * FROM users where id = ?";

            try (Connection connection = DBHelper.getConnection();
                 PreparedStatement ps = connection.prepareStatement(selecSQL);) {
                ps.setInt(1, (int)fromUserId);
                ResultSet resultSet = ps.executeQuery();
//			System.out.println("Total rows is " + resultSet.last());
//			System.out.println("rows = " + resultSet.getFetchSize());
                if (resultSet.next()) {
                    boolean hasEnough = resultSet.getFloat("balance") >= amount;
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    boolean Vip = resultSet.getBoolean("Vip");
                    float balance = resultSet.getFloat("balance");
                    System.out.println("record > id = " + fromUserId + " name = " + name + " balance = " + balance);
                    resultSet.close();
                    new User(name, age, email, password,fromUserId, Vip, balance);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;

        }

    @Override
    public boolean transferWithTransaction(long fromUserId, long toUserId, float amount) {

        {
            if (amount <= 0) {
                System.out.println("no tienes suficiente dinero" + amount);
            }


            // paso1 : consulta los datos de los dos usuarios para chequear si existen ellos
            // y cumplen las condiciones
            // primero creamos las querys de select para hacer select para compobar quelos usuarios exiten  y update
            // para hacer la trasferencia de dinero
            String selectSQL1 = "select * from users where id =  " + fromUserId + " For Update";
            String selectSQL2 = "select * from users where id =  " + toUserId + " For Update";
            String updSQL1 = "UPDATE users SET balance = balance - " + amount + " where id = " + fromUserId;
            String updSQL2 = "UPDATE users SET balance = balance + " + amount + " where id = " + toUserId;
            /**
             * creamos 1 un objeto de conexion y otros dos para el ejecutar los dos selects
             */  Connection connection = DBHelper.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(selectSQL1);
                 //iniciar una transaccion de multiples lecturas y escrituras que se dependen entre si
                 //Si uno de los pasos falla, nos permite deshacer todos los cambios en esta transaccion
                 PreparedStatement ps2 = connection.prepareStatement(selectSQL2);) {

                // *CAMBIO NECESARIO 1: Desactivar autocommit para iniciar la transacción*
                // hace que se ejecute de uno en uno para que no colapsen a la vez
                connection.setAutoCommit(false);
                // empieza la transaccion
                /**
                 * creamos los parametros que podamos insertar desde la terminal
                 */
                // ps.setLong(1, FromUserId);
                // se crea la variable con la ejecucion de la query
                ResultSet resultSet = ps.executeQuery();
                //  si el resultado es correcto que siga ejecutando
                if (resultSet.next()) {
                    // se crea otra variable que sea boolean comprobando que si balance es mayor que amount se pueda ejecutar
                    boolean enoughBalance = resultSet.getFloat("balance") >= amount;
                    if (enoughBalance) {
                        // aqui comprueba que toUserId es igual que a la base de datos
                        // ps2.setLong(1, toUserId);
                        ResultSet resultSet1 = ps2.executeQuery();
                        if (resultSet1.next()) {
                            /**
                             * aqui se crea una variable llamada rowsUpdate que contiene las
                             * lineas actualizadas y tambien que se ejecute el update
                             */
                            PreparedStatement ps3 = connection.prepareStatement(updSQL1);
                            int rowsUpdate = ps3.executeUpdate();
                            System.out.println("rows update = " + rowsUpdate);
                            // aqui dice que si rowsupdate se ha actualizado al menos una vez se ejecute lo de abajo
                            if (rowsUpdate > 0) {

                                ps3 = connection.prepareStatement(updSQL2);
                                rowsUpdate = ps3.executeUpdate();
                                System.out.println("rows update = " + rowsUpdate);
                                connection.commit();
                                return true; // Retornar true si la transferencia fue exitosa
                            }

                        }
                    }

                    resultSet.close();

                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


            // paso 2 : ejecutamos los sql de update

            return false;
        }

    }

    @Override
    public boolean transferWithTransactionWithoutDeadlock(long fromUserId, long toUserId, float amount) {
        {

            {
                if (amount <= 0) {
                    System.out.println("no tienes suficiente dinero" + amount);
                }

                //evitar dead lock ordenando los ids de los usuarios,
                // siempre empleamos lock en las filas con el mismo orden
                long first = Math.min(fromUserId, toUserId);
                long second = Math.max(fromUserId, toUserId);

                // paso1 : consulta los datos de los dos usuarios para chequear si existen ellos
                // y cumplen las condiciones
                // primero creamos las querys de select para hacer select para compobar quelos usuarios exiten  y update
                // para hacer la trasferencia de dinero
                String selectSQL1 = "select * from users where id =  " + first + " For Update";
                String selectSQL2 = "select * from users where id =  " + second + " For Update";
                String updSQL1 = "UPDATE users SET balance = balance - " + amount + " where id = " + first;
                String updSQL2 = "UPDATE users SET balance = balance + " + amount + " where id = " + second;
                /**
                 * creamos 1 un objeto de conexion y otros dos para el ejecutar los dos selects
                 */  Connection connection = DBHelper.getConnection();
                try (PreparedStatement ps = connection.prepareStatement(selectSQL1);
                     //iniciar una transaccion de multiples lecturas y escrituras que se dependen entre si
                     //Si uno de los pasos falla, nos permite deshacer todos los cambios en esta transaccion
                     PreparedStatement ps2 = connection.prepareStatement(selectSQL2);) {

                    // *CAMBIO NECESARIO 1: Desactivar autocommit para iniciar la transacción*
                    // hace que se ejecute de uno en uno para que no colapsen a la vez
                    connection.setAutoCommit(false);
                    // empieza la transaccion
                    /**
                     * creamos los parametros que podamos insertar desde la terminal
                     */
                    // ps.setLong(1, FromUserId);
                    // se crea la variable con la ejecucion de la query
                    ResultSet resultSet = ps.executeQuery();
                    //  si el resultado es correcto que siga ejecutando
                    if (resultSet.next()) {
                        // se crea otra variable que sea boolean comprobando que si balance es mayor que amount se pueda ejecutar
                        boolean enoughBalance = resultSet.getFloat("balance") >= amount;
                        if (enoughBalance) {
                            // aqui comprueba que toUserId es igual que a la base de datos
                            // ps2.setLong(1, toUserId);
                            ResultSet resultSet1 = ps2.executeQuery();
                            if (resultSet1.next()) {
                                /**
                                 * aqui se crea una variable llamada rowsUpdate que contiene las
                                 * lineas actualizadas y tambien que se ejecute el update
                                 */
                                PreparedStatement ps3 = connection.prepareStatement(updSQL1);
                                int rowsUpdate = ps3.executeUpdate();
                                System.out.println("rows update = " + rowsUpdate);
                                // aqui dice que si rowsupdate se ha actualizado al menos una vez se ejecute lo de abajo
                                if (rowsUpdate > 0) {

                                    ps3 = connection.prepareStatement(updSQL2);
                                    rowsUpdate = ps3.executeUpdate();
                                    System.out.println("rows update = " + rowsUpdate);
                                    connection.commit();
                                    return true; // Retornar true si la transferencia fue exitosa
                                }

                            }
                        }

                        resultSet.close();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }


                // paso 2 : ejecutamos los sql de update

                return false;
            }

        }

    }
}

