package org.mul_table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ReportFrame extends JFrame implements ActionListener {
    JButton go_back_button;

    HighScoreTables tables;
    ExercisesStatistic statistics;

    public ReportFrame(HighScoreTables tables, ExercisesStatistic statistics) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setTitle("Reports");
        setLocationRelativeTo(null);
        setLayout(null);
        this.add_components();
        setVisible(true);

        this.tables = tables;
        this.statistics = statistics;
    }

    private void add_components() {
        this.go_back_button = new JButton("Go back");
        this.go_back_button.setBounds(350, 450, 100, 50);
        this.go_back_button.addActionListener(this);
        this.add(this.go_back_button);

        for (ExerciseStatistic statistic: this.statistics.get_statistic_array()) {
            JLabel new_label = new JLabel();
            new_label.setBounds(50, 50, 300, 50);
            // TODO: dump statistic here
            new_label.setText("");
            this.add(new_label);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.go_back_button)
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
