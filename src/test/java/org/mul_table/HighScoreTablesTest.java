package org.mul_table;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;

class HighScoreTablesTest {
    @Test
    void test_add_score() {
        HighScoreTables tables = new HighScoreTables();
        ExerciseStatistic statistic = new ExerciseStatistic(null, null, new Exercise(1, 2, 3, "easy"));
        statistic.set_accuracy_score(99);
        statistic.set_speed_score(98);
        tables.add_score(statistic);
        statistic = new ExerciseStatistic(null, null, new Exercise(1, 2, 3, "easy"));
        statistic.set_accuracy_score(79);
        statistic.set_speed_score(78);
        tables.add_score(statistic);
        statistic = new ExerciseStatistic(null, null, new Exercise(1, 2, 3, "easy"));
        statistic.set_accuracy_score(89);
        statistic.set_speed_score(88);
        tables.add_score(statistic);
        Assertions.assertTrue(tables.get_tables().get(0)
                .get_scores().stream().sorted(new Comparator<ExerciseStatistic>() {
                    @Override
                    public int compare(ExerciseStatistic s_1, ExerciseStatistic s_2) {
                        return -Double.compare(s_1.get_accuracy_score() + s_1.get_speed_score(),
                                s_2.get_accuracy_score() + s_2.get_speed_score());
                    }
                }).collect(Collectors.toList())
                .equals(tables.get_tables().get(0).get_scores()));
    }
}
