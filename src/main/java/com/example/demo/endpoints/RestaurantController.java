package com.example.demo.endpoints;


import com.example.demo.data.MySqlDataRepository;
import com.example.demo.data.RestDataRepository;
import com.example.demo.data.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/restaurants", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantController {
    RestDataRepository repo;

    public RestaurantController() {
        repo = new MySqlDataRepository();
    }

    @GetMapping("")
    public List<Restaurant> getRestaurants() {
        return repo.getList(Restaurant.class);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        return repo.getById(Restaurant.class, id);
    }

    @GetMapping("search/{name}")
    public List<Restaurant> getRestaurantsByName(@PathVariable String name) {
        return repo.getByName(name);
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ValidationResultDto> addRestaurant(@RequestBody Restaurant restaurant) {
        var result = restaurant.validate();

        if(!result.isValid()) {
            return new ResponseEntity(new ValidationResultDto(result, null), HttpStatus.OK);
        }

        var newId = repo.addRestaurant(restaurant);
        var newCity = repo.getById(Restaurant.class, newId);

        return ResponseEntity.ok(new ValidationResultDto(result, newCity));

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ValidationResultDto> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {

        if(id != restaurant.getId()) {
            return new ResponseEntity(new ValidationResultDto("Url id does not match restaurant id"), HttpStatus.OK);
        }

        var validationResult = restaurant.validate();

        if(!validationResult.isValid()) {
            return new ResponseEntity(new ValidationResultDto(validationResult, null), HttpStatus.OK);
        }

        var result = repo.updateRestaurant(restaurant);
        var res = repo.getById(Restaurant.class, id);

        return ResponseEntity.ok(new ValidationResultDto(validationResult, res));
    }


}
