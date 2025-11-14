/**
 * 
 */
package user;

import model.User;

/**
 * @author G 16 oct 2025
 */
//Insertar un conjunto de usuarios en la base de datos
public interface UserDaoInterface {

	public int insertAll(User[] users);
	/**
	 * Transferir dinero de un usuario a otro
	 * @param fromUserId  id del enviador
	 * @param toUserId	 id del receptor
	 * @param amount	 la cantidad mandada
	 * @return			 true si se ejecuta correctamente
	 */
	public boolean transfer(long fromUserId, long toUserId, float amount);
	/**
	 * Transferir dinero de un usuario a otro
	 * @param fromUserId  id del enviador
	 * @param toUserId	 id del receptor
	 * @param amount	 la cantidad mandada
	 * @return			 true si se ejecuta correctamente
	 */
	public boolean transferWithBatch(long fromUserId, long toUserId, float amount);
    /**
     * Transferir dinero de un usuario a otro
     * @param fromUserId  id del enviador
     * @param toUserId	 id del receptor
     * @param amount	 la cantidad mandada
     * @return			 true si se ejecuta correctamente
     */
    public boolean transferWithQueryAndUpdate(long fromUserId, long toUserId, float amount);
    /**
     * Transferir dinero de un usuario a otro
     * @param fromUserId  id del enviador
     * @param toUserId	 id del receptor
     * @param amount	 la cantidad mandada
     * @return			 true si se ejecuta correctamente
     */
    public boolean transferWithTransaction(long fromUserId, long toUserId, float amount);
    /**
     * Transferir dinero de un usuario a otro
     * @param fromUserId  id del enviador
     * @param toUserId	 id del receptor
     * @param amount	 la cantidad mandada
     * @return			 true si se ejecuta correctamente
     */
    public boolean transferWithTransactionWithoutDeadlock(long fromUserId, long toUserId, float amount);
    /**
     * Transferir dinero de un usuario a otro
     * @param fromUserId  id del enviador
     * @param toUserId	 id del receptor
     * @param amount	 la cantidad mandada
     * @return			 true si se ejecuta correctamente
     */
}
