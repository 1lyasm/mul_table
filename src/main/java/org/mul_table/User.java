package org.mul_table;

class User {
	private final String username;
	private final String password;
	boolean is_admin;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.is_admin = false;
	}
	public User(String username, String password, boolean is_admin) {
		this.username = username;
		this.password = password;
		this.is_admin = is_admin;
	}
	public String get_passw() {return password; }
	public String get_username() {return username; }
	public boolean get_is_admin() {return this.is_admin; }
	
	public void set_is_admin() {this.is_admin = true; }
}

