package org.example;
import java.time.LocalDateTime;

public class User {

    public enum Gender {
        NOT_SPECIFIED, MALE, FEMALE
    }

    private Integer id;
    private String surname;
    private String name;
    private Gender gender;
    private LocalDateTime authorizationTime;

    // Конструктори, гетери та сетери
    public User(Integer id, String surname, String name, Gender gender, LocalDateTime authorizationTime) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.gender = gender;
        this.authorizationTime = authorizationTime;
    }

    public Integer getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDateTime getAuthorizationTime() {
        return authorizationTime;
    }
}
