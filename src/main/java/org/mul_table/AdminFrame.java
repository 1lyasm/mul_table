package org.mul_table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

class AdminFrame extends JFrame implements ActionListener {
    JLabel a_label;
    JTextField a_field;

    JLabel b_label;
    JTextField b_field;
    JLabel n_label;
    JTextField n_field;
    JLabel e_name_label;
    JTextField e_name_field;
    JButton to_practice_button;
    JButton add_exercise_button;
    JButton report_button;
    Exercises exercises;
    Users users;
    Serializer serializer;
    ExercisesStatistic statistics;
    HighScoreTables tables;

    public AdminFrame(Users users, Serializer serializer, Exercises exercises, HighScoreTables tables, ExercisesStatistic statistics) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setTitle("Admin");
        setLocationRelativeTo(null);
        setLayout(null);
        add_components();
        setVisible(true);
        this.users = users;
        this.serializer = serializer;
        this.exercises = exercises;
        this.statistics = statistics;
        this.tables = tables;
    }

    public void add_components() {
        a_label = new JLabel("a: ");
        a_label.setBounds(260, 150, 300, 40);
        a_label.setFont(new Font("Serif", Font.PLAIN, 24));

        a_field = new JTextField();
        a_field.setBounds(300, 150, 200, 40);

        b_label = new JLabel("b: ");
        b_label.setBounds(260, 200, 300, 40);
        b_label.setFont(new Font("Serif", Font.PLAIN, 24));

        b_field = new JTextField();
        b_field.setBounds(300, 200, 200, 40);

        n_label = new JLabel("N: ");
        n_label.setBounds(260, 250, 300, 40);
        n_label.setFont(new Font("Serif", Font.PLAIN, 24));

        n_field = new JTextField();
        n_field.setBounds(300, 250, 200, 40);

        e_name_label = new JLabel("Name: ");
        e_name_label.setBounds(220, 300, 300, 40);
        e_name_label.setFont(new Font("Serif", Font.PLAIN, 24));

        e_name_field = new JTextField();
        e_name_field.setBounds(300, 300, 200, 40);

        to_practice_button = new JButton("To practice");
        to_practice_button.setBounds(300, 370, 150, 40);
        to_practice_button.addActionListener(this);

        add_exercise_button = new JButton("Add exercise");
        add_exercise_button.setBounds(300, 440, 150, 40);
        add_exercise_button.addActionListener(this);

        this.report_button = new JButton("View reports");
        this.report_button.setBounds(300, 490, 150, 40);
        this.report_button.addActionListener(this);

        add(a_label);
        add(a_field);
        add(b_label);
        add(b_field);
        add(n_label);
        add(n_field);
        add(e_name_label);
        add(e_name_field);
        add(to_practice_button);
        add(add_exercise_button);
        add(this.report_button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == to_practice_button) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if (actionEvent.getSource() == add_exercise_button) {
            try {
                int a = Integer.parseInt(a_field.getText());
                int b = Integer.parseInt(b_field.getText());
                int n = Integer.parseInt(n_field.getText());
                String name = e_name_field.getText();
                if (a < 1 || a > 10 || b < 1 || b > 10 || n < 1)
                    JOptionPane.showMessageDialog(this, "Parameters not in range");
                else {
                    boolean is_duplicate = false;
                    for (Exercise e: this.exercises.get_exercise_array())
                        if (e.get_name().equals(name))
                            is_duplicate = true;
                    if (is_duplicate)
                        JOptionPane.showMessageDialog(this, "Duplicate exercise names not allowed");
                    else {
                        this.exercises.get_exercise_array().add(new Exercise(a, b, n, name));
                        JOptionPane.showMessageDialog(this, "Added new exercise mode: " + name);
                    }
                }
            }
            catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(this, "Enter valid numbers");
            }
        }
        else if (actionEvent.getSource() == this.report_button) {
            ReportFrame report_frame = new ReportFrame(this.tables, this.statistics);
            this.setVisible(false);
            report_frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    setVisible(true);

                    report_frame.dispose();
                }
            });
        }
    }

    public Users get_users() {
        return this.users;
    }

    public Exercises get_exercises() {
        return this.exercises;
    }
}
