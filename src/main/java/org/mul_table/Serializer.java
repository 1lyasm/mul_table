package org.mul_table;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class Serializer {
    File data_dir;
    File users_file;
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
        gson = new Gson();

    }

    public Users deserialize() {
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

    public void serialize(Users users) {
        String json_string = gson.toJson(users);
        try {
            Files.write(users_file.toPath(), json_string.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file");
            System.exit(1);
        }
    }
}
