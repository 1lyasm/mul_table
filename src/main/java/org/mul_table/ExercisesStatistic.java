package org.mul_table;

import java.util.ArrayList;

public class ExercisesStatistic {
    ArrayList<ExerciseStatistic> statistic_array;

    public ExercisesStatistic() {
        this.statistic_array = new ArrayList<ExerciseStatistic>();
    }

    public void add_statistic(ExerciseStatistic statistic) {
        this.statistic_array.add(statistic);
    }
}
