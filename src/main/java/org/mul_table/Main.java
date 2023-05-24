package org.mul_table;

public class Main {
    Serializer serializer;
    public Main() {
	serializer = new Serializer();
	Users users = serializer.deserialize();
        LoginFrame login_frame = new LoginFrame(users, serializer);
    }
    

    public static void main(String[] args) {
	    new Main();
    }
}

