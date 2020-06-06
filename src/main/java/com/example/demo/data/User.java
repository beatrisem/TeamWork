package com.example.demo.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    public static final String SELECT_QUERY = "select * from " + Constants.SCHEMA_NAME + ".users";
    private String username;
    private String password;

    public static String getSelectSql() {
        return SELECT_QUERY;
    }

    public static User getUser(ResultSet rs) {
        return User.createUser(rs);
    }

    public static User createUser(ResultSet rs) {
        User user = null;
        try {
            user = new User(rs.getString("username"),
                    rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '}';
    }
}
