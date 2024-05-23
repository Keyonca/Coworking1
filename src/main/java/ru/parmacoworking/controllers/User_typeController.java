package ru.parmacoworking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.parmacoworking.entity.User_type;
import ru.parmacoworking.service.User_typeService;


import java.util.List;

@RestController
@RequestMapping("api/v1/user_type")
public class User_typeController {

    private final User_typeService user_typeService;

    @Autowired
    public User_typeController(User_typeService user_typeService) {
        this.user_typeService = user_typeService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('user_type', 'read')")
    public List<User_type> findAll(){
        return user_typeService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('user_type', 'read')")
    public User_type findById(@PathVariable String id){
        return user_typeService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasPermission('user_type', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    public User_type create(@RequestBody User_type user_type){
        return user_typeService.create(user_type);
    }

    @PutMapping
    @PreAuthorize("hasPermission('user_type', 'update')")
    @ResponseStatus(HttpStatus.OK)
    public User_type update(@RequestBody User_type user_type){
        return user_typeService.update(user_type);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('user_type', 'delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        user_typeService.delete(id);
    }
}
