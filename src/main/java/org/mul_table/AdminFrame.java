package org.mul_table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class AdminFrame extends JFrame implements ActionListener {
    JLabel a_label;
    JTextField a_field;

    JLabel b_label;
    JTextField b_field;

    JLabel n_label;
    JTextField n_field;
    JButton to_practice_button;
    JButton add_exercise_button;

    Users users;
    Serializer serializer;

    public AdminFrame(Users users) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setTitle("Admin");
        setLocationRelativeTo(null);
        setLayout(null);
        add_components();
        setVisible(true);
        this.users = users;
        this.serializer = serializer;
    }

    public void add_components() {
        a_label = new JLabel("a: ");
        a_label.setBounds(260, 200, 300, 40);
        a_label.setFont(new Font("Serif", Font.PLAIN, 24));

        a_field = new JTextField();
        a_field.setBounds(300, 200, 200, 40);

        b_label = new JLabel("b: ");
        b_label.setBounds(260, 250, 300, 40);
        b_label.setFont(new Font("Serif", Font.PLAIN, 24));

        b_field = new JTextField();
        b_field.setBounds(300, 250, 200, 40);

        n_label = new JLabel("N: ");
        n_label.setBounds(260, 300, 200, 40);
        n_label.setFont(new Font("Serif", Font.PLAIN, 24));

        n_field = new JTextField();
        n_field.setBounds(300, 300, 200, 40);

        to_practice_button = new JButton("To practice");
        to_practice_button.setBounds(300, 370, 150, 40);
        to_practice_button.addActionListener(this);

        add_exercise_button = new JButton("Add exercise");
        add_exercise_button.setBounds(300, 420, 150, 40);
        add_exercise_button.addActionListener(this);

        add(a_label);
        add(a_field);
        add(b_label);
        add(b_field);
        add(n_label);
        add(n_field);
        add(to_practice_button);
        add(add_exercise_button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == to_practice_button) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    public Users get_users() {return this.users; }
}

