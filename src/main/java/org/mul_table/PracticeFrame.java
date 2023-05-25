package org.mul_table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class PracticeFrame extends JFrame implements ActionListener {
	JButton start_button;
	JButton default_e_button;
	JButton submit_button;
	JLabel timer_label;
	JLabel mode_choice_label;
	JLabel question_label;
	Users users;
	int time_seconds;
	ScheduledThreadPoolExecutor scheduler;
	Exercises exercises;
	ArrayList<JButton> e_buttons;
	Serializer serializer;
	Exercise current_mode;

	public PracticeFrame(Users users, Serializer serializer, Exercises exercises) {
		this.users = users;
		this.exercises = exercises;
		this.serializer = serializer;
		this.time_seconds = 0;
		this.current_mode = new Exercise(10, 10, 10, "Default");
		this.question_label = new JLabel();
		this.add(this.question_label);
		this.submit_button = new JButton("Submit");
		this.submit_button.setBounds(330, 350, 150, 40);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Multiplication Table Practice");
		setLocationRelativeTo(null);
		setLayout(null);
		this.e_buttons = new ArrayList<JButton>();
		this.init_e_buttons();
		this.add_components();
		setVisible(true);
    }

    public void add_components() {
        start_button = new JButton("Start");
        start_button.setBounds(330, 300, 150, 40);
        start_button.addActionListener(this);

        timer_label = new JLabel("00:00", SwingConstants.CENTER);
        timer_label.setBounds(350, 50, 100, 40);
		timer_label.setFont(new Font("Serif", Font.PLAIN, 24));

		JLabel e_label = new JLabel("Modes");
		e_label.setBounds(660, 50, 100, 40);
		e_label.setFont(new Font("Serif", Font.PLAIN, 24));

		this.default_e_button = new JButton("Default");
		this.default_e_button.setBounds(650, 100, 80, 40);
		this.default_e_button.addActionListener(this);
		this.e_buttons.add(default_e_button);

		this.mode_choice_label = new JLabel("Mode Default");
		this.mode_choice_label.setBounds(340, 200, 300, 40);
		this.mode_choice_label.setFont(new Font("Serif", Font.PLAIN, 24));

        this.add(start_button);
        this.add(timer_label);
		this.add(this.default_e_button);
		this.add(e_label);
		this.add(this.mode_choice_label);
    }

	public void init_e_buttons() {
		int i = 1;
		for (Exercise exercise: this.exercises.get_exercise_array()) {
			JButton new_button = new JButton(exercise.get_name());
			new_button.setBounds(650, 100 + 50 * i, 80, 40);
			new_button.addActionListener(this);
			this.e_buttons.add(new_button);
			this.add(new_button);
			++i;
		}
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
			this.remove(this.mode_choice_label);
			this.revalidate();
			this.repaint();
			Random rand = new Random();
			this.question_label.setText(rand.nextInt(this.current_mode.get_a() + 1) + " x " +
						rand.nextInt(this.current_mode.get_b() + 1) + " = ");
			this.question_label.setBounds(340, 200, 300, 40);
			this.question_label.setFont(new Font("Serif", Font.PLAIN, 24));
			for (JButton b: this.e_buttons) {
				b.setEnabled(false);
			}
			this.add(submit_button);
		}
		else if (actionEvent.getSource() == this.default_e_button) {
			this.current_mode = new Exercise(10, 10, 10, "Default");
			this.mode_choice_label.setText("Mode Default");
		}
		else {
			for (JButton e_button: e_buttons) {
				if (actionEvent.getSource() == e_button) {
					for (Exercise e: this.exercises.get_exercise_array()) {
						if (e_button.getText().equals(e.get_name())) {
							this.current_mode = e;
						}
					}
					this.mode_choice_label.setText("Mode " + this.current_mode.get_name());
				}
			}
		}
    }
}

