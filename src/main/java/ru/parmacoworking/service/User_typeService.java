package ru.parmacoworking.service;

import ru.parmacoworking.entity.User_type;

import java.util.List;

public interface User_typeService {

    List<User_type> findAll();

    User_type findById(Object id);

    User_type create(User_type user_type);

    User_type update(User_type user_type);

    void delete(Object id);
}
