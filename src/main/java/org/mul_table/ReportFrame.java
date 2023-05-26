package org.mul_table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReportFrame extends JFrame implements ActionListener {
    JButton go_back_button;

    HighScoreTables tables;
    ExercisesStatistic statistics;

    public ReportFrame(HighScoreTables tables, ExercisesStatistic statistics) {
        this.tables = tables;
        this.statistics = statistics;


        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setTitle("Reports");
        setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add_components();
        setVisible(true);
    }

    private void add_components() {
        this.go_back_button = new JButton("Go back");
        this.go_back_button.setBounds(350, 450, 100, 50);
        this.go_back_button.addActionListener(this);
        this.add(this.go_back_button);

        JTextArea text_area = new JTextArea();
        text_area.setBounds(50, 50, 500, 500);
        String report_string = "";
        report_string += "user\tis_admin\tstart\t\ttotal_dur\ta\tb\tgiven\tcorrect\tdur\tspeed_score\tacc_score\tmode_a\tmode_b\tn\tmode_name\n";

        for (ExerciseStatistic statistic: this.statistics.get_statistic_array()) {
            for (Question q: statistic.get_questions()) {
                report_string += statistic.get_user().get_username() + "\t" +
                        statistic.get_user().get_is_admin() + "\t" +
                        statistic.get_start_time().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\t" +
                        statistic.get_total_duration().toSeconds() + "\t" +
                        q.get_a() + "\t" +
                        q.get_b() + "\t" +
                        q.get_given_answer() + "\t" +
                        q.get_correct_answer() + "\t" +
                        q.get_answer_duration().toSeconds() + "\t" +
                        statistic.get_speed_score() + "\t" +
                        statistic.get_accuracy_score() + "\t" +
                        statistic.get_e_mode().get_a() + "\t" +
                        statistic.get_e_mode().get_b() + "\t" +
                        statistic.get_e_mode().get_n() + "\t" +
                        statistic.get_e_mode().get_name() + "\n";
            }
        }

        // TODO: add save table as txt and xlsx

        text_area.setText(report_string);
        this.add(text_area);
        JScrollPane scroll = new JScrollPane (text_area,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(scroll, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.go_back_button)
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
