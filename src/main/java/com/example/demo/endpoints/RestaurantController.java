package com.example.demo.endpoints;

import com.example.demo.ValidationResultDto;
import com.example.demo.data.MySqlDataRepository;
import com.example.demo.data.RestDataRepository;
import com.example.demo.data.Restaurant;
import com.example.demo.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/search/{name}")
    public List<Restaurant> getRestaurantsByName(@PathVariable("name")  String name) {
        return repo.getByName(name);
    }

//    @PostMapping("/names")
//    public List<String> getRestNames() {
//        var result = repo.getList(Restaurant.class);
//        return result.stream().map(m->m.getName()).collect(Collectors.toList());
//    }

    @GetMapping("/filtered")
    public List<Restaurant> getRestaurantsFiltered(@RequestParam String query) {
        var result = repo.getList(Restaurant.class);

        if(!query.isEmpty()) {
            return result.stream().filter(s->s.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
        }

        return result;
    }



    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ValidationResultDto> addRestaurant(@RequestBody Restaurant restaurant) {
        var result = restaurant.validate();

        if(!result.isValid()) {
            return new ResponseEntity(new ValidationResultDto(result, null), HttpStatus.OK);
        }

        var newId = repo.addRestaurant(restaurant);
        var newRest = repo.getById(Restaurant.class, newId);

        return ResponseEntity.ok(new ValidationResultDto(result, newRest));

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


    @GetMapping("/user/{username}")
    public Restaurant getByUserName(@PathVariable String username) {
        return repo.getByUserName(username);
    }


    @PostMapping({"/login", "/login/"})
    public User login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        return repo.getUserByUserNameAndPassword(username, password);
    }



}