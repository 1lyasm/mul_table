package org.mul_table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class LoginFrame extends JFrame implements ActionListener {
    JPasswordField password_field;
    JTextField username_field;
    JLabel password_label;
    JLabel username_label;
    JLabel message_label;
    JButton sign_in_button;
    JButton reset_button;
    JCheckBox show_password_checkbox;
    JButton register_button;
    Users users;
    Serializer serializer;
    public LoginFrame(Users users, Serializer serializer) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setTitle("Login");
        setLocationRelativeTo(null);
        setLayout(null);
	    add_components();
        setVisible(true);
        this.users = users;
        this.serializer = serializer;
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

	    register_button = new JButton("Register");
        register_button.setBounds(500, 350, 100, 40);
        register_button.addActionListener(this);

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
        add(register_button);
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
                PracticeFrame practice_frame;
                Exercises exercises = this.serializer.deserialize_exercises();
                if (users.get_user_by_uname_passw(username_text, password_text).get_is_admin()) {
                    JOptionPane.showMessageDialog(this, "You logged in successfully as admin");
                    practice_frame = new AdminPracticeFrame(users, serializer, exercises, new User(username_text, password_text));
                } else {
                    JOptionPane.showMessageDialog(this, "You logged in successfully");
                    practice_frame = new PracticeFrame(users, serializer, exercises, new User(username_text, password_text));
                }
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else {
                JOptionPane.showMessageDialog(this, "Username or password incorrect");
            }
        }
        else if (actionEvent.getSource() == register_button) {
            RegistrationFrame register_frame = new RegistrationFrame(this.users);
            this.setVisible(false);
            register_frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(true);
                    users.set_user_array(register_frame.get_users().get_user_array());
                    register_frame.dispose();
                    serializer.serialize_users(users);
                }
            });
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

