package org.mul_table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminPracticeFrame extends PracticeFrame {
    JButton admin_button;
    int last_e_len;
    public AdminPracticeFrame(Users users, Serializer serializer, Exercises exercises, User logged_in_user, HighScoreTables tables, ExercisesStatistic statistics) {
        super(users, serializer, exercises, logged_in_user, tables, statistics);
        add_admin_components();
    }

    public void add_admin_components() {
        admin_button = new JButton("Admin page");
        admin_button.setBounds(330, 400, 150, 40);
        admin_button.addActionListener(this);

        this.add(admin_button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);
        if (actionEvent.getSource() == admin_button) {
            AdminFrame admin_frame = new AdminFrame(this.users, this.serializer, this.exercises, this.tables, this.statistics);
            this.setVisible(false);
            AdminPracticeFrame this_frame = this;
            admin_frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    setVisible(true);
                    users.set_user_array(admin_frame.get_users().get_user_array());
                    exercises.set_exercise_array(admin_frame.get_exercises().get_exercise_array());
                    if (exercises.get_exercise_array().size() > last_e_len) {
                        int new_button_count = exercises.get_exercise_array().size() - last_e_len;
                        for (int i = new_button_count - 1; i >= 0; --i) {
                            JButton new_e_button = new JButton(
                                    exercises.get_exercise_array()
                                            .get(exercises.get_exercise_array().size() - i - 1).get_name());
                            new_e_button.setBounds(650, e_buttons.get(e_buttons.size() - 1).getY() + 50, 80, 40);
                            new_e_button.addActionListener(this_frame);
                            if (has_started) {
                                new_e_button.setEnabled(false);
                            }
                            e_buttons.add(new_e_button);
                            this_frame.add(new_e_button);
                        }
                        last_e_len = exercises.get_exercise_array().size();
                    }
                    admin_frame.dispose();
                    serializer.serialize_exercises(exercises);
                }
            });
        }
    }
}
