package ru.parmacoworking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('user', 'read')")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('user', 'read')")
    public User findById(@PathVariable String id){
        return userService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasPermission('user', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @PutMapping
    @PreAuthorize("hasPermission('user', 'update')")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('user', 'delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        userService.delete(id);
    }
}
