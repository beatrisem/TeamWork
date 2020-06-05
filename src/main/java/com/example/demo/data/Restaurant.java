package com.example.demo.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Restaurant implements EntityBase {

    public static final String SELECT_QUERY = "select * from " + Constants.SCHEMA_NAME + ".eateries";
    private int id;
    private String name;
    private String city;
    private String address;
    private String district;
    private int freeTables;
    private int maxTables;
    private BigDecimal lat;
    private BigDecimal lng;
    private String homepage;
    private String phone;


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
        if(name.isEmpty()) {
            result.addError("Name is empty");
        }

        if(city.isEmpty()) {
            result.addError("City is empty");
        }

        if(freeTables > maxTables){
            result.addError("Exceeds maximum capacity");
        }

        if(freeTables < 0 || maxTables < 0){
            result.addError("Table count must be a positive value");
        }

        return result;
    }


    public static Restaurant createRestaurant(ResultSet rs) {
        Restaurant restaurant = null;
        try {
            restaurant = new Restaurant(rs.getInt("id_rest"),
                    rs.getString("name_rest"),
                    rs.getString("city_rest"),
                    rs.getString("address_rest"),
                    rs.getString("district_rest"),
                    rs.getInt("free_tables_rest"),
                    rs.getInt("max_free_tables_rest"),
                    rs.getBigDecimal("lat_rest"),
                    rs.getBigDecimal("lng_rest"),
                    rs.getString("homepage_rest"),
                    rs.getString("phone_rest"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurant;
    }

    public Restaurant(int id, String name, String city, String address, String district, int freeTables, int maxTables, BigDecimal lat, BigDecimal lng, String homepage, String phone) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.district = district;
        this.freeTables = freeTables;
        this.maxTables = maxTables;
        this.lat = lat;
        this.lng = lng;
        this.homepage = homepage;
        this.phone = phone;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getFreeTables() {
        return freeTables;
    }

    public void setFreeTables(int freeTables) {
        this.freeTables = freeTables;
    }

    public int getMaxTables() {
        return maxTables;
    }

    public void setMaxTables(int maxTables) {
        this.maxTables = maxTables;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", freeTables=" + freeTables +
                ", maxTables=" + maxTables +
                ", lat=" + lat +
                ", lng=" + lng +
                ", homepage='" + homepage + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
