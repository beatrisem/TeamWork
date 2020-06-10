package com.example.demo.data;

import java.util.List;

public interface RestDataRepository {

    <T extends EntityBase> List<T> getList(Class<T> item);

    <T extends EntityBase> T getById(Class<T> item, int id);

    <T extends EntityBase> void deleteById(Class<T> item, int id);

    int addRestaurant(Restaurant restaurant);

    int updateRestaurant(Restaurant restaurant);

    int addUser(User user);

    List<Restaurant> getRestaurants();

    List<Restaurant> getByStreet(String street);

    List<Restaurant> getByCity(String city);

    List<Restaurant> getByDistrict(String district);

    List<Restaurant> getByName(String name);

    User getUserByUserName(String name);

    Restaurant getByUserName(String name);

    User getUserByUserNameAndPassword(String name, String password);

    void updateFreeTableCount(Restaurant rest, int count);

}
