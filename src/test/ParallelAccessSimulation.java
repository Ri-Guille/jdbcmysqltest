/**
 * 
 */
package Test;

import java.util.ArrayList;

import user.UserDao;

/**
 * @author G 10 oct 2025
 */
public class ParallelAccessSimulation {
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		long fromUserId = 1;
		long toUserId = 2;
		ArrayList<Thread> threads = new ArrayList<Thread>(100);
		System.out.println(threads.size());
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    userDao.transferWithQueryAndUpdate(fromUserId, toUserId, 1);
                }

			});
			threads.add(thread);
		}
		for (Thread thread : threads) {
			thread.start();
			
		}
	}
}
