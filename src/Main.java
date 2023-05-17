import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class Main {
    Users users;
    Serializer serializer;
    public Main() {
	serializer = new Serializer();
	this.users = serializer.deserialize();
        LoginFrame login_frame = new LoginFrame(users);
	login_frame.addWindowListener(new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent e) {
			users.set_user_array(login_frame.get_users().get_user_array());
			login_frame.dispose();
			users.print_users();
			serializer.serialize(users);
		}
	});
    }
    

    public static void main(String[] args) {
	    new Main();
    }
}

