package ru.parmacoworking.service;

import ru.parmacoworking.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Object id);

    User create(User user);

    User update(User user);

    void delete(Object id);
}
