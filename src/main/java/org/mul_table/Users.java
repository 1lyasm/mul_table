package org.mul_table;

import java.util.ArrayList;

public class Users {
	private ArrayList<User> user_array;

	public void add_user(User user) {
		user_array.add(user);
	}

	public boolean user_exists(String username, String password) {
		for (User user : user_array) {
			if (password.equals(user.get_passw()) &&
					username.equals(user.get_username())) {
				return true;
			}
		}
		return false;
	}

	public User get_user_by_uname_passw(String username, String password) {
		for (User user : user_array) {
			if (password.equals(user.get_passw()) &&
					username.equals(user.get_username())) {
				return user;
			}
		}
		return null;
	}
	
	public boolean username_exists(String username) {
		for (User user : user_array) {
			if (username.equals(user.get_username())) {
				return true;
			}
		}
		return false;
	}

	public int get_user_count() {
		return user_array.size();
	}

	public ArrayList<User> get_user_array() {
		return user_array;
	}

	public void set_user_array(ArrayList<User> user_array) {
		this.user_array = user_array;
	}

	public void print_users() {
		for (User user: this.user_array) {
			System.out.print("(" + user.get_username() + ", " + user.get_passw() + ") ");
		}
	}

	public Users () {
		user_array = new ArrayList<User>();
	}
}
