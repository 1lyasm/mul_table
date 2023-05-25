package org.mul_table;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class Serializer {
    File data_dir;
    File users_file;
    File exercises_file;
    Gson gson;

    public Serializer() {
        data_dir = new File("mul_table_data");
        if (!data_dir.canRead()) {
            data_dir.mkdir();
        }
        users_file = new File("mul_table_data/users.json");
        if (!users_file.canRead()) {
            try {
                users_file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
                System.exit(1);
            }
        }
        this.exercises_file = new File("mul_table_data/exercises.json");
        if (!this.exercises_file.canRead()) {
            try {
                this.exercises_file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
                System.exit(1);
            }
        }
        gson = new Gson();
    }

    public Users deserialize_users() {
        Users users = new Users();
        try {
            String json_string = new String(Files.readAllBytes(users_file.toPath()));
            users = gson.fromJson(json_string, Users.class);
            if (users == null) {
                users = new Users();
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            System.exit(1);
        }
        return users;
    }

    public Exercises deserialize_exercises() {
        Exercises exercises = new Exercises();
        System.out.println("cant deser");
        try {
            String json_string = new String(Files.readAllBytes(exercises_file.toPath()));
            exercises = gson.fromJson(json_string, Exercises.class);
            if (exercises == null) {
//                System.out.println("cant deser");
                exercises = new Exercises();
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            System.exit(1);
        }
        return exercises;
    }

    public void serialize_users(Users users) {
        String json_string = gson.toJson(users);
        try {
            Files.write(users_file.toPath(), json_string.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file");
            System.exit(1);
        }
    }

    public void serialize_exercises(Exercises exercises) {
        String json_string = gson.toJson(exercises);
        try {
            Files.write(exercises_file.toPath(), json_string.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file");
            System.exit(1);
        }
    }
}
