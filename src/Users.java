import java.util.ArrayList;

class Users {
	private ArrayList<User> user_array;

	public void add_user(User user) {
		user_array.add(user);
	}

	public boolean user_exists(String username, String password) {
		for (int i = 0; i < user_array.size(); ++i) {
			if (password.equals(user_array.get(i).get_passw()) &&
				username.equals(user_array.get(i).get_username())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean username_exists(String username) {
		for (int i = 0; i < user_array.size(); ++i) {
			if (username.equals(user_array.get(i).get_username())) {
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

