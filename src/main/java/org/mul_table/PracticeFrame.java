package org.mul_table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Font;

class PracticeFrame extends JFrame implements ActionListener {
	JButton start_button;
	JLabel timer_label;
	Users users;
	int time_seconds;
	ScheduledThreadPoolExecutor scheduler;
    public PracticeFrame(Users users) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        	setSize(800, 600);
        	setTitle("Multiplication Table Practice");
        	setLocationRelativeTo(null);
        	setLayout(null);
		this.add_components();
        	setVisible(true);
		this.users = users;
		this.time_seconds = 0;
    }

    public void add_components() {
        start_button = new JButton("Start");
        start_button.setBounds(350, 300, 100, 40);
        start_button.addActionListener(this);

        timer_label = new JLabel("00:00", SwingConstants.CENTER);
        timer_label.setBounds(350, 50, 100, 40);
	timer_label.setFont(new Font("Serif", Font.PLAIN, 24));

        this.add(start_button);
        this.add(timer_label);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
	if (actionEvent.getSource() == this.start_button) {
		Runnable timer = new Runnable() {
			public void run() {
				time_seconds++;
				int minutes_part = time_seconds / 60;
				int seconds_part = time_seconds % 60;
				String minutes_string = Integer.toString(minutes_part);
				String seconds_string = Integer.toString(seconds_part);
				if (minutes_part < 10 && seconds_part < 10) {
	    				timer_label.setText("0" + minutes_string + ":0" + seconds_string);
				}
				else if (minutes_part < 10 && seconds_part >= 10) {
	    				timer_label.setText("0" + minutes_string + ":" + seconds_string);
				}
				else if (minutes_part >= 10 && seconds_part < 10) {
	    				timer_label.setText(minutes_string + ":0" + seconds_string);
				}
				else {
	    				timer_label.setText(minutes_string + ":" + seconds_string);
				}
			}
		};
		scheduler = new ScheduledThreadPoolExecutor(2);
		scheduler.scheduleWithFixedDelay(timer, 1, 1, TimeUnit.SECONDS);
		start_button.setEnabled(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scheduler.shutdownNow();
			}
		});
	}
    }
}

