package org.mul_table;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

class Serializer {
    File data_dir;
    File users_file;
    File exercises_file;
    File exercises_statistic_file;
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
        this.exercises_statistic_file = new File("mul_table_data/exercises_statistics.json");
        if (!this.exercises_statistic_file.canRead()) {
            try {
                this.exercises_statistic_file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
                System.exit(1);
            }
        }
        this.gson = new Gson();
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).registerTypeAdapter(Duration.class, new JsonDeserializer<Duration>() {
            @Override
            public Duration deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return Duration.ZERO.plus(json.getAsJsonPrimitive().getAsLong(), ChronoUnit.NANOS);
            }
        })
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {

                    @Override
                    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }
                })
                .registerTypeAdapter(Duration.class, new JsonSerializer<Duration>() {

                    @Override
                    public JsonElement serialize(Duration src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.toNanos());
                    }
                })
                 .setPrettyPrinting()
                .create();
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

    public void serialize_users(Users users) {
        String json_string = gson.toJson(users);
        try {
            Files.write(users_file.toPath(), json_string.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file");
            System.exit(1);
        }
    }

    public Exercises deserialize_exercises() {
        Exercises exercises = new Exercises();
        try {
            String json_string = new String(Files.readAllBytes(exercises_file.toPath()));
            exercises = gson.fromJson(json_string, Exercises.class);
            if (exercises == null) {
                exercises = new Exercises();
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            System.exit(1);
        }
        return exercises;
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

    public ExercisesStatistic deserialize_exercises_statistic() {
        ExercisesStatistic e_s = new ExercisesStatistic();
        try {
            String json_string = new String(Files.readAllBytes(this.exercises_statistic_file.toPath()));
            e_s = gson.fromJson(json_string, ExercisesStatistic.class);
            if (e_s == null) {
                e_s = new ExercisesStatistic();
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            System.exit(1);
        }
        return e_s;
    }

    public void serialize_exercises_statistic(ExercisesStatistic exercises_statistic) {
        String json_string = gson.toJson(exercises_statistic);
        try {
            Files.write(exercises_statistic_file.toPath(), json_string.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file");
            System.exit(1);
        }
    }
}
