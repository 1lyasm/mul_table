import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.json.Json;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class MainFrame {
    private Users users;

    MainFrame() {
	init_users();
        LoginPage login_page = new LoginPage(users);
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    private void init_users() {
	    // read from file
	    this.users = new Users();
    }
}

class LoginPage extends JFrame implements ActionListener {
    JPasswordField password_field;
    JTextField username_field;
    JLabel password_label;
    JLabel username_label;
    JLabel message_label;
    JLabel title_label;
    JButton sign_in_button;
    JButton reset_button;
    JCheckBox show_password_checkbox;
    Users users;
    
    public LoginPage(Users users) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Multiplication Table Practice");
        setLocationRelativeTo(null);
        setLayout(null);
	add_components();
        setVisible(true);
	this.users = users;
    }

    public void add_components() {
        password_field = new JPasswordField();
        password_field.setBounds(300, 200, 300, 40);

        password_label = new JLabel("Password: ");
        password_label.setBounds(200, 200, 100, 40);

        username_label = new JLabel("Username: ");
        username_label.setBounds(200, 150, 100, 40);

        username_field = new JTextField();
        username_field.setBounds(300, 150, 300, 40);

        sign_in_button = new JButton("Sign in");
        sign_in_button.setBounds(300, 300, 100, 40);
        sign_in_button.addActionListener(this);

        reset_button = new JButton("Reset");
        reset_button.setBounds(500, 300, 100, 40);
        reset_button.addActionListener(this);

        message_label = new JLabel("");
        message_label.setBounds(300, 350, 300, 40);

        show_password_checkbox = new JCheckBox("Show password");
        show_password_checkbox.setBounds(300, 250, 300, 40);
        show_password_checkbox.addActionListener(this);

        add(password_field);
        add(password_label);
        add(username_label);
        add(username_field);
        add(sign_in_button);
        add(message_label);
        add(reset_button);
        add(show_password_checkbox);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == sign_in_button) {
            String username_text = username_field.getText();
            String password_text = new String(password_field.getPassword());
            if (users.user_exists(username_text, password_text)) {
                JOptionPane.showMessageDialog(this, "You logged in successfully");
            }

            else {

	        System.out.println("Hi");
		if (users.get_user_count() == 0) {
                	JOptionPane.showMessageDialog(this, "You registered successfully as admin");
		}
		else {
                	JOptionPane.showMessageDialog(this, "You registered successfully");
		}
		users.add_user(new User(username_text, password_text));
            }
	    PracticePage practice_page = new PracticePage();
	    practice_page.run();
        }
        else if (actionEvent.getSource() == reset_button) {
            username_field.setText("");
            password_field.setText("");
        }
        else if (actionEvent.getSource() == show_password_checkbox) {
            if (show_password_checkbox.isSelected()) {
                password_field.setEchoChar((char) 0);
            }
            else {
                password_field.setEchoChar('*');
            }
        }
    }
}


class PracticePage extends JFrame {
	public PracticePage() {
		;
	}

	public void run() {
        	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	this.setSize(800, 600);
        	this.setTitle("Practice");
        	this.setLocationRelativeTo(null);
        	this.setLayout(null);
        	this.setVisible(true);
	}
}

class User {
	private String username;
	private String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String get_passw() {return password; }
	public String get_username() {return username; }
}

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

