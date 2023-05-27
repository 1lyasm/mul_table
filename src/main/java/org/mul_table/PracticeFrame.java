package org.mul_table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class PracticeFrame extends JFrame implements ActionListener {
	JButton start_button;
	JButton default_e_button;
	JButton submit_button;
	JButton exit_button;
	JLabel timer_label;
	JLabel mode_choice_label;
	JLabel question_label;
	JLabel score_info_label;
	JTextField answer_field;
	Users users;
	int time_seconds;
	ScheduledThreadPoolExecutor scheduler;
	Exercises exercises;
	ArrayList<JButton> e_buttons;
	Serializer serializer;
	Exercise current_mode;
	boolean has_started;
	ExerciseStatistic exercise_statistic;
	User logged_in_user;
	Random rand;
	HighScoreTables tables;
	ArrayList<JLabel> score_labels = new ArrayList<>();
	ExercisesStatistic statistics;

	public PracticeFrame(Users users, Serializer serializer, Exercises exercises, User logged_in_user, HighScoreTables tables, ExercisesStatistic statistics) {
		this.users = users;
		this.exercises = exercises;
		this.serializer = serializer;
		this.time_seconds = 0;
		this.current_mode = new Exercise(10, 10, 10, "Default");
		this.question_label = new JLabel();
		this.add(this.question_label);
		this.submit_button = new JButton("Submit");
		this.submit_button.setBounds(330, 350, 150, 40);
		this.submit_button.addActionListener(this);
		this.has_started = false;
		this.logged_in_user = logged_in_user;
		this.tables = tables;
		this.statistics = statistics;
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

		this.exit_button = new JButton("Exit");
		this.exit_button.setBounds(330, 450, 150, 40);
		this.exit_button.addActionListener(this);

		this.mode_choice_label = new JLabel("Mode Default");
		this.mode_choice_label.setBounds(340, 200, 300, 40);
		this.mode_choice_label.setFont(new Font("Serif", Font.PLAIN, 24));

        this.add(start_button);
        this.add(timer_label);
		this.add(this.default_e_button);
		this.add(this.exit_button);
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
			rand = new Random();
			exercise_statistic = new ExerciseStatistic(LocalDateTime.now(), this.logged_in_user, this.current_mode);
			int a = rand.nextInt(this.current_mode.get_a() + 1);
			int b = rand.nextInt(this.current_mode.get_b() + 1);
			exercise_statistic.get_questions().add(new Question(a, b));
			this.question_label.setText(a + " x " + b + " = ");
			this.question_label.setBounds(250, 200, 300, 40);
			this.question_label.setFont(new Font("Serif", Font.PLAIN, 24));
			for (JButton button: this.e_buttons)
				button.setEnabled(false);
			this.add(submit_button);
			this.answer_field = new JTextField();
			this.answer_field.setBounds(340, 200, 200, 40);
			this.add(this.answer_field);
			this.has_started = true;
			this.show_high_score_table();
		}
		else if (actionEvent.getSource() == this.submit_button) {
			try {
				int given_answer = Integer.parseInt(this.answer_field.getText());
				this.exercise_statistic.get_questions().get(this.exercise_statistic.get_questions().size() - 1).set_given_answer(given_answer);
				String answer_result;
				if (this.exercise_statistic.get_questions().get(this.exercise_statistic.get_questions().size() - 1).is_false()) {
					answer_result = new String("false");
				}
				else {
					answer_result = new String("true");
				}
				JOptionPane.showMessageDialog(this, "Your answer is " + answer_result);
				this.exercise_statistic.get_questions().get(this.exercise_statistic.get_questions().size() - 1).set_answer_duration(
						Duration.between(this.exercise_statistic.get_start_time().plus(this.exercise_statistic.get_total_duration()),
								LocalDateTime.now()));
				this.exercise_statistic.extend_total_duration(this.exercise_statistic.get_questions().get(this.exercise_statistic.get_questions().size() - 1).get_answer_duration());
				if (this.exercise_statistic.get_questions().size() == this.current_mode.get_n()) {
					this.scheduler.shutdownNow();
					this.exercise_statistic.calculate_scores();
					JOptionPane.showMessageDialog(this, "Speed score: " + this.exercise_statistic.get_speed_score()
							+ "\nAccuracy score: " + this.exercise_statistic.get_accuracy_score() + "");
					this.remove(this.submit_button);
					this.remove(this.question_label);
					this.remove(this.answer_field);
					for (JLabel label: this.score_labels)
						this.remove(label);
					this.revalidate();
					this.repaint();
					this.tables.add_score(this.exercise_statistic);
					this.show_high_score_table();
				}
				else {
					int a = rand.nextInt(this.current_mode.get_a() + 1);
					int b = rand.nextInt(this.current_mode.get_b() + 1);
					exercise_statistic.get_questions().add(new Question(a, b));
					this.question_label.setText(a + " x " + b + " = ");
				}
			}
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "You did not enter an integer");
			}
			this.answer_field.setText("");
		}
		else if (actionEvent.getSource() == this.exit_button) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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

	private void show_high_score_table() {
		this.score_info_label = new JLabel("High scores");
		this.score_info_label.setBounds(30, 30, 300, 40);
		this.score_info_label.setFont(new Font("Serif", Font.PLAIN, 24));
		this.add(this.score_info_label);

		int last_y = 50;
		HighScoreTable current_table = null;
		for (HighScoreTable table: this.tables.get_tables())
			if (table.get_exercise().get_name().equals(this.current_mode.get_name()))
				current_table = table;

		if (current_table == null)
			return;

		this.score_labels = new ArrayList<>();

		for (ExerciseStatistic statistic: current_table.get_scores()) {
			double average_score = Math.round(100 * (statistic.get_speed_score() + statistic.get_accuracy_score())) / 200.0;
			JLabel new_score = new JLabel(statistic.get_user().get_username() + " - " + average_score);
			new_score.setBounds(30, last_y + 30, 300, 40);
			new_score.setFont(new Font("Serif", Font.PLAIN, 16));
			this.add(new_score);
			this.score_labels.add(new_score);
			last_y += 30;
		}
	}

	public ExerciseStatistic get_exercise_statistic() {
		return this.exercise_statistic;
	}

	public HighScoreTables get_tables() {
		return this.tables;
	}
}

