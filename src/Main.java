import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String args[]) {
	Users users = new Users();
        LoginFrame login_frame = new LoginFrame(users);
    }
}

