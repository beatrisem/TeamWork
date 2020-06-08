package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class MySqlDataRepository implements RestDataRepository {

    private Connection connection;
    private static final String connectionString = "jdbc:mysql://localhost:3306/rest_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String connectionUser = "root";
    private static final String connectionPassword = "Admin12345*";

    public MySqlDataRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public <T extends EntityBase> List<T> getList(Class<T> cl) {
        List<T> items = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            Statement statement = connection.createStatement();

            var getSqlQueryMethod = cl.getMethod("getSelectSql");
            var sql = getSqlQueryMethod.invoke(null);

            ResultSet rs = statement.executeQuery(sql.toString());
            var createObjectMethod = cl.getMethod("getEntity", ResultSet.class);

            while (rs.next()) {

                items.add((T) createObjectMethod.invoke(null, rs));
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public <T extends EntityBase> T getById(Class<T> cl, int id) {
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            var getSqlQueryMethod = cl.getMethod("getSelectByIdSql");
            var sql = getSqlQueryMethod.invoke(null);

            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            var createObjectMethod = cl.getMethod("getEntity", ResultSet.class);

            while (rs.next()) {
                return (T) createObjectMethod.invoke(null, rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int addRestaurant(Restaurant restaurant) {
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser,connectionPassword);

            CallableStatement stmt = connection.prepareCall("{call spAddRestaurant(?,?,?,?,?,?,?,?,?,?,?,?)}");
            stmt.setString("name_rest", restaurant.getName());
            stmt.setString("city_rest", restaurant.getCity());
            stmt.setString("address_rest", restaurant.getAddress());
            stmt.setString("district_rest", restaurant.getDistrict());
            stmt.setInt("free_tables_rest", restaurant.getFreeTables());
            stmt.setInt("max_free_tables_rest", restaurant.getMaxTables());
            stmt.setBigDecimal("lng_rest", restaurant.getLng());
            stmt.setBigDecimal("lat_rest", restaurant.getLat());
            stmt.setString("homepage_rest", restaurant.getHomepage());
            stmt.setString("phone_rest", restaurant.getPhone());
            stmt.setString("username_rest", restaurant.getUsername());
            stmt.registerOutParameter("id_rest", Types.INTEGER);
            stmt.execute();

            var id = stmt.getInt("id_rest");

            return id;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateRestaurant(Restaurant restaurant) {
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            CallableStatement stmt = connection.prepareCall("{call spUpdateRestaurant(?,?,?,?,?,?,?,?,?,?)}");
            stmt.setInt(("id"), restaurant.getId());
            stmt.setString("restname", restaurant.getName());
            stmt.setString("city", restaurant.getCity());
            stmt.setString("address", restaurant.getAddress());
            stmt.setString("district", restaurant.getDistrict());
            stmt.setInt("freetables", restaurant.getFreeTables());
            stmt.setInt("maxtables", restaurant.getMaxTables());
            stmt.setString("homepage", restaurant.getHomepage());
            stmt.setString("phone", restaurant.getPhone());

            stmt.registerOutParameter("rows_affected", Types.INTEGER);

            stmt.execute();

            return stmt.getInt("rows_affected");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public <T extends EntityBase> void deleteById(Class<T> cl, int id) {
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            var getSqlQueryMethod = cl.getMethod("getDeleteByIdSql");
            var sql = getSqlQueryMethod.invoke(null);

            CallableStatement stmt = connection.prepareCall(sql.toString());
            stmt.setInt("id", id);

            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(Restaurant.SELECT_QUERY);

            while (rs.next()) {
                Restaurant rest = new Restaurant(rs.getInt("id_rest"), rs.getString("name_rest"), rs.getString("city_rest"), rs.getString("address_rest"), rs.getString("district_rest"), rs.getInt("free_tables_rest"), rs.getInt("max_free_tables_rest"), rs.getBigDecimal("lat_rest"),
                        rs.getBigDecimal("lng_rest"), rs.getString("phone_rest"),
                        rs.getString("homepage_rest"), rs.getString("username_rest"));
                restaurants.add(rest);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }


    @Override
    public List<Restaurant> getByStreet(String street) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(Restaurant.SELECT_QUERY + " where address_rest like '%" + street + "%'");
            while (rs.next()) {
                var rest = Restaurant.createRestaurant(rs);
                restaurants.add(rest);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return restaurants;
    }


    @Override
    public List<Restaurant> getByCity(String city) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            PreparedStatement stmt = connection.prepareStatement(Restaurant.SELECT_QUERY + " where city_rest = ?");
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var rest = Restaurant.createRestaurant(rs);
                restaurants.add(rest);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return restaurants;
    }

    @Override
    public List<Restaurant> getByDistrict(String district) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            PreparedStatement stmt = connection.prepareStatement(Restaurant.SELECT_QUERY + " where district_rest = ?");
            stmt.setString(1, district);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var rest = Restaurant.createRestaurant(rs);
                restaurants.add(rest);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return restaurants;
    }

    @Override
    public List<Restaurant> getByName(String name) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(Restaurant.SELECT_QUERY + " where name_rest like '%" + name + "%'");
            while (rs.next()) {
                var rest = Restaurant.createRestaurant(rs);
                restaurants.add(rest);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public User getUserByUserName(String username) {
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(Restaurant.SELECT_QUERY + " where username_rest ='" + username + "'");
            while (rs.next()) {
                return User.createUser(rs);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Restaurant getByUserName(String name) {
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(Restaurant.SELECT_QUERY + " where username_rest ='" + name + "'");
            while (rs.next()) {
                return Restaurant.createRestaurant(rs);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User getUserByUserNameAndPassword(String username, String password) {
        User user = null;
        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(User.SELECT_QUERY + " where username = '" + username + "' and password = '" + password + "'");
            if (rs.next()) {
                user = User.createUser(rs);
            }
            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateFreeTableCount(Restaurant rest, int count) {

        try {
            connection = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            Statement stmt = connection.createStatement();

            stmt.executeUpdate("update rest_db.eateries set free_tables_rest = free_tables_rest + " + count + " where name_rest = " + "'" + rest.getName() + "' and id_rest = " + rest.getId());

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}