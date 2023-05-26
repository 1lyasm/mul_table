package org.mul_table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighScoreTables {
    ArrayList<HighScoreTable> tables = new ArrayList<>();
    public void add_score(ExerciseStatistic statistic) {
        int e_index = -1;
        for (int i = 0; i < this.tables.size(); ++i)
            // TODO: enforce cant have exercise modes with duplicate names
            if (this.tables.get(i).get_exercise().get_name().equals(statistic.get_e_mode().get_name()))
                e_index = i;
        if (e_index == -1) {
            this.tables.add(new HighScoreTable(statistic.get_e_mode()));
            this.tables.get(this.tables.size() - 1).get_scores().add(statistic);
        }
        else {
            this.tables.get(e_index).get_scores().add(statistic);
            Collections.sort(this.tables.get(e_index).get_scores(), new Comparator<ExerciseStatistic>() {
                @Override
                public int compare(ExerciseStatistic s_1, ExerciseStatistic s_2) {
                    return -Double.compare(s_1.get_accuracy_score() + s_1.get_speed_score(), s_2.get_accuracy_score() + s_2.get_speed_score());
                }
            });
        }
    }

    public HighScoreTables() {
        this.tables = new ArrayList<>();
    }
}

class HighScoreTable {
    Exercise exercise;
    ArrayList<ExerciseStatistic> scores = new ArrayList<>();

    public Exercise get_exercise() {
        return this.exercise;
    }

    public HighScoreTable(Exercise exercise) {
        this.exercise = exercise;
    }

    public ArrayList<ExerciseStatistic> get_scores() {
        return this.scores;
    }
}
