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
	
	public int get_user_count() {
		return user_array.size();
	}

	public Users () {
		user_array = new ArrayList<User>();
	}
}

