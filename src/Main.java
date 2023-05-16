import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import com.google.gson.Gson; 
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;


public class Main {
    public static void main(String args[]) {
	Users users = Serializer.deserialize();
        LoginFrame login_frame = new LoginFrame(users);
    }
}
	
class Serializer {
	public static Users deserialize() {
		Users users = new Users();
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
			String json_string = new String(Files.readAllBytes(users_file.toPath()));
			Gson gson = new Gson();
			users = gson.fromJson(json_string, Users.class);
			if (users == null) {
				users = new Users();
			}
		} catch (IOException e) {
			System.out.println("Error reading file");
			System.exit(1);
		}
		return users;
	}
}

