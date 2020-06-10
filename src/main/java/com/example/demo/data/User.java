package com.example.demo.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements EntityBase{

    public static final String SELECT_QUERY = "select * from " + Constants.SCHEMA_NAME + ".users";
    private String username;
    private String password;
    private int enabled;
    private int user_id;


    public static String getSelectSql() {
        return SELECT_QUERY;
    }

    public static EntityBase getEntity(ResultSet rs) {
        return Restaurant.createRestaurant(rs);
    }

    public static String getSelectByIdSql() {
        return getSelectSql() + " where id_rest = ?";
    }

    public static String getDeleteByIdSql() {
        return "{call spDeleteRestaurant(?)}";
    }

    @Override
    public ValidationResult validate() {
        var result = new ValidationResult();
        if(username.isEmpty()) {
            result.addError("Username is empty");
        }

        if(password.isEmpty()) {
            result.addError("Password is empty");
        }

        return result;
    }

    public static User getUser(ResultSet rs) {
        return User.createUser(rs);
    }

    public static User createUser(ResultSet rs) {
        User user = null;
        try {
            user = new User(rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("enabled"),
                    rs.getInt("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }



    public User(String username, String password, int enabled, int user_id) {
        this.username = username;
        this.password = password;
        this.enabled = this.enabled;
        this.user_id = this.user_id;
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", user_id=" + user_id +
                '}';
    }
}
