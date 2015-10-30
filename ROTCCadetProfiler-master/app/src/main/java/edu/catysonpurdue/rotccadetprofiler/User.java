package edu.catysonpurdue.rotccadetprofiler;

public class User {

    String email, username, password;
    int age;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this("", username, password);
    }
}