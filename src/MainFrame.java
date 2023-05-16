// Set external_libraries as dependency in your IDE
// For Intellij, File -> Project structure -> Project settings -> Modules ->
//  Dependencies -> + -> jars or directories -> [path of external_dependencies] -> OK

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.json.Json;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;

public class MainFrame extends JFrame implements ActionListener {
    private LoginPage login_page;

    MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Multiplication Table Practice");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        login_page = new LoginPage(this);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        login_page.actionPerformed(actionEvent, this);
    }
}

class LoginPage {
    JPasswordField password_field;
    JTextField username_field;
    JLabel password_label;
    JLabel username_label;
    JLabel message_label;
    JLabel title_label;
    JButton sign_in_button;
    JButton reset_button;
    JCheckBox show_password_checkbox;
    JButton register_button;

    public LoginPage(MainFrame main_frame) {
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
        sign_in_button.addActionListener(main_frame);

        reset_button = new JButton("Reset");
        reset_button.setBounds(500, 300, 100, 40);
        reset_button.addActionListener(main_frame);

        message_label = new JLabel("");
        message_label.setBounds(300, 350, 300, 40);

        show_password_checkbox = new JCheckBox("Show password");
        show_password_checkbox.setBounds(300, 250, 300, 40);
        show_password_checkbox.addActionListener(main_frame);

        register_button = new JButton("Register");
        register_button.setBounds(300, 350, 100, 40);
        register_button.addActionListener(main_frame);

        main_frame.add(password_field);
        main_frame.add(password_label);
        main_frame.add(username_label);
        main_frame.add(username_field);
        main_frame.add(sign_in_button);
        main_frame.add(message_label);
        main_frame.add(reset_button);
        main_frame.add(show_password_checkbox);
        main_frame.add(register_button);
    }

    public void actionPerformed(ActionEvent actionEvent, MainFrame main_frame) {
        if (actionEvent.getSource() == sign_in_button) {
            String username_text = username_field.getText();
            String password_text = new String(password_field.getPassword());
            if (username_text == "A" && password_text == "B") {
                JOptionPane.showMessageDialog(main_frame, "You logged in successfully");
            }
            else {
                JOptionPane.showMessageDialog(main_frame, "Invalid username or password");
            }
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
