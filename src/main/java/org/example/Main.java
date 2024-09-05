package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Створення колекції List<User>
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Макаренко", "Ігор", User.Gender.MALE, LocalDateTime.now()));
        users.add(new User(2, "Степаненко", "Ольга", User.Gender.FEMALE, LocalDateTime.now()));
        users.add(new User(3, "Іванов", "Іван", User.Gender.NOT_SPECIFIED, LocalDateTime.now()));

        // Створення об'єкта Gson з адаптером для LocalDateTime
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();

        // Перетворення колекції в JSON
        String json = gson.toJson(users);

        // Запис JSON у файл за допомогою Apache Commons IO
        File file = new File("users.json");
        try {
            FileUtils.writeStringToFile(file, json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Читання JSON з файлу
        try {
            String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            System.out.println("JSON з файлу:");
            System.out.println(fileContent);

            // Перетворення JSON у Set<User>
            User[] usersArray = gson.fromJson(fileContent, User[].class);
            Set<User> usersSet = new HashSet<>(Arrays.asList(usersArray));

            // Виведення Set<User>
            usersSet.forEach(user -> System.out.println(user.getName() + " " + user.getSurname()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}