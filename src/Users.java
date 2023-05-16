import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import com.google.gson.Gson; 

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
		File data_dir = new File("mul_table_data");
		if (!data_dir.canRead()) {
			data_dir.mkdir();
		}
		File users_file = new File("mul_table_data/users.json");
		if (!users_file.canRead()) {
			try {
 				users_file.createNewFile();
			} catch(IOException e) {
				System.out.println("Error creating file");
				System.exit(1);
			}
		}
		try {
			StringReader reader = new StringReader(new String(Files.readAllBytes(users_file.toPath())));
			Gson gson = new Gson();
			user_array = new ArrayList<User>();
		} catch (IOException e) {
			System.out.println("Error reading file");
			System.exit(1);
		}
	}
}

