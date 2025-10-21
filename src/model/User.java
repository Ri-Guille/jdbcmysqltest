package model;

public class User {
	public String name = "Alejandro";
	private int age;
	private String email;
	private String password;
	private long id;
	private boolean Vip;
	private float Balance;

//Los atributos
	public User(Long id, String name) {
	}

	public User() {
	}

	public User(int i, String name2) {
		// TODO Auto-generated constructor stub

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isVip() {
		return Vip;
	}

	public void setVip(boolean vip) {
		Vip = vip;
	}
	
	public float getBalance() {
		return Balance;
	}

	public void setBalance(float balance) {
		Balance = balance;
	}

	public User(long id) {
		this.id = id;
	}
	public User(String name, int age, String email, String password, long id, boolean Vip, float Balance) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
		this.id = id;
		this.Vip = Vip;
		this.Balance = Balance;
		
	}
}
