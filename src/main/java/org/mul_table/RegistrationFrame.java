package org.mul_table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

class RegistrationFrame extends JFrame implements ActionListener {
    JPasswordField password_field;
    JTextField username_field;
    JLabel password_label;
    JLabel username_label;
    JLabel message_label;
    JButton register_button;
    JButton reset_button;
    JCheckBox show_password_checkbox;
    Users users;

    public RegistrationFrame(Users users) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setTitle("Register");
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

        register_button = new JButton("Register");
        register_button.setBounds(300, 300, 100, 40);
        register_button.addActionListener(this);

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
        add(register_button);
        add(message_label);
        add(reset_button);
        add(show_password_checkbox);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
	if (actionEvent.getSource() == register_button) {
		String username_text = username_field.getText();
		String password_text = new String(password_field.getPassword());
		if (username_text.length() == 0 || password_text.length() == 0) {
			JOptionPane.showMessageDialog(this, "Username or password can not be empty");
			return;
		}
		if (users.username_exists(username_text)) {
			JOptionPane.showMessageDialog(this, "User already exists");
			return;
		}
		User new_user = new User(username_text, password_text);
		if (users.get_user_count() == 0) {
					JOptionPane.showMessageDialog(this, "You registered successfully as admin");
					new_user.set_is_admin();
		}
		else {
					JOptionPane.showMessageDialog(this, "You registered successfully");
		}
		this.users.add_user(new_user);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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

    public Users get_users() {return this.users; }
}


