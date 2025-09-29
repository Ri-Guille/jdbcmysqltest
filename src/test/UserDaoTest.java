/**
 * 
 */
package Test;

import user.UserDao;

/**
 * @author G 29 sept 2025
 */
public class UserDaoTest {
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		userDao.insert(0, null, null, null, null, false);
	}

}
