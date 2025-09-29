package user;

import model.User;

class Test {
	public static void main(String[] args) {
		User user1 = new User();
		System.err.println();
		User user2 = new User(1, "Alejandro");
		System.out.print(user2.name);
		user1.setName("Luis");
		System.out.println(user1.getName());
		User user3 = new User("Fernando", 10, "email@gmail.com", "joselu123", 35 ,true);
		System.out.println(user3.getPassword());
	}
}
