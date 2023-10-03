package com.Daniel.Model;

import org.junit.jupiter.api.Assertions;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

@Entity
@Table(name = "\"users\"", schema="\"bbddlocal\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String login;
    @Column
    private String password;
    private User user;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }

    public User(int id, String name, String surname, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof User)) {
//            return false;
//        }
//        User user = (User) obj;
//        Field[] fields = user.getClass().getFields();
//        Field[] fieldsThis = this.getClass().getFields();
//        for (Field f : fields) {
//            if (f != null) {
//                return false;
//            }
//            else
//        }
//
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
