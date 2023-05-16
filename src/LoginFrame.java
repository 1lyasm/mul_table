import javax.swing.*;
import javax.json.Json;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.awt.event.WindowEvent;

class LoginFrame extends JFrame implements ActionListener {
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
    
    public LoginFrame(Users users) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setTitle("Login");
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
		if (users.get_user_count() == 0) {
                	JOptionPane.showMessageDialog(this, "You registered successfully as admin");
		}
		else {
                	JOptionPane.showMessageDialog(this, "You registered successfully");
		}
		users.add_user(new User(username_text, password_text));
            }
	    PracticeFrame practice_frame = new PracticeFrame(users);
	    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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

