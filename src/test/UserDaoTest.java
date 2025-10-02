/**
 * 
 */
package Test;

import java.security.PublicKey;

import user.UserDao;

/**
 * @author G 29 sept 2025
 */
public class UserDaoTest {
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		userDao.insert(0, null, 10, null, null, false);
		
		userDao.delete(0);
		
	}
	
}
