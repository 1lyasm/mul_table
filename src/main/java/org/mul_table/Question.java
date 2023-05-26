package org.mul_table;

import java.time.Duration;

public class Question {
    int a;

    int b;
    int given_answer;
    int correct_answer;
    Duration answer_duration;

    public Question(int a, int b) {
        this.a = a;
        this.b = b;
        this.correct_answer = this.a * this.b;
        this.answer_duration = Duration.ZERO;
    }

    public void set_given_answer(int given_answer) {
        this.given_answer = given_answer;
    }

    public void set_answer_duration(Duration answer_duration) {
        this.answer_duration = answer_duration;
    }

    public Duration get_answer_duration() {
        return this.answer_duration;
    }

    public boolean is_false() {
        return this.correct_answer != this.given_answer;
    }

    public int get_a() {
        return a;
    }

    public int get_b() {
        return b;
    }

    public int get_given_answer() {
        return given_answer;
    }

    public int get_correct_answer() {
        return correct_answer;
    }
}
