package edu.catysonpurdue.rotccadetprofiler;

public class User {

    String email, username, password;
    String rank;

    public User(String email, String username, String password, String rank) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.rank = rank;
    }

    public User(String username, String password) {
        this("", username, password, "");
    }
}