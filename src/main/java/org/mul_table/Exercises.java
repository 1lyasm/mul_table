package org.mul_table;

import java.util.ArrayList;

class Exercises {
    private ArrayList<Exercise> exercise_array;

    public Exercises() {
        exercise_array = new ArrayList<Exercise>();
    }

    public void add_exercise(Exercise exercise) {
        this.exercise_array.add(exercise);
    }

    public void print_exercises() {
        for (Exercise exercise: this.exercise_array)
            System.out.print(exercise);
    }
    public void set_exercise_array(ArrayList<Exercise> exercise_array) {
        this.exercise_array = exercise_array;
    }

    public ArrayList<Exercise> get_exercise_array() {
        return this.exercise_array;
    }
}
