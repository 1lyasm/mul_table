package org.mul_table;

class Exercise {
    int a;
    int b;
    int n;
    String name;

    public int get_a() {
        return a;
    }

    public int get_b() {
        return b;
    }

    public int get_n() {
        return n;
    }

    public String get_name() {
        return name;
    }

    public void set_a(int a) {
        this.a = a;
    }

    public void set_b(int b) {
        this.b = b;
    }

    public void set_n(int n) {
        this.n = n;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public Exercise(int a, int b, int n, String name) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.name = name;
    }
}
