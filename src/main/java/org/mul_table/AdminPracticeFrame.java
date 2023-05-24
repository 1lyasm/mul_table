package org.mul_table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AdminPracticeFrame extends PracticeFrame {
    JLabel admin_label;
    JButton admin_button;
    public AdminPracticeFrame(Users users) {
        super(users);
        add_admin_components();
    }

    public void add_admin_components() {
        admin_button = new JButton("Admin page");
        admin_button.setBounds(330, 350, 150, 40);
        admin_button.addActionListener(this);

        this.add(admin_button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);
        if (actionEvent.getSource() == admin_button) {
            AdminFrame admin_frame = new AdminFrame(this.users);
            this.setVisible(false);
            admin_frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(true);
                    users.set_user_array(admin_frame.get_users().get_user_array());
                    admin_frame.dispose();
                }
            });
        }
    }
}
