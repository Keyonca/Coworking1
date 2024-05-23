package ru.parmacoworking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.entity.User_type;
import ru.parmacoworking.exceptions.EntityAlreadyExistsException;
import ru.parmacoworking.exceptions.EntityIllegalArgumentException;
import ru.parmacoworking.exceptions.EntityNotFoundException;
import ru.parmacoworking.jpa.User_typeRepository;
import ru.parmacoworking.service.User_typeService;

import java.util.List;

@Service
public class DefaultUser_typeService implements User_typeService {

    private final User_typeRepository user_typeRepository;

    @Autowired
    public DefaultUser_typeService(User_typeRepository user_typeRepository) {
        this.user_typeRepository = user_typeRepository;
    }

    public List<User_type> findAll() {
        return user_typeRepository.findAll();
    }

    public User_type findById(Object id) {
        User_type user_type;
        if (id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Long parsedId;
        try {
            parsedId = Long.valueOf((String) id);
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор" +
                    "к нужному типу, текст ошибки %s", ex));
        }

        user_type = user_typeRepository.findOne(parsedId);

        if (user_type == null) {
            throw new EntityNotFoundException(User.TYPE_NAME, parsedId);
        }
        return user_type;
    }

    public User_type create(User_type user_type) {
        if (user_type == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (user_type.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        User_type existedUser_type = user_typeRepository.findOne(user_type.getId());
        if (existedUser_type != null) {
            throw new EntityAlreadyExistsException(User.TYPE_NAME, user_type.getId());
        }
        return user_typeRepository.save(user_type);
    }

    @Override
    public User_type update(User_type user_type) {
        if (user_type == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (user_type.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        User_type existedUser_type = user_typeRepository.findOne(user_type.getId());
        if (existedUser_type == null) {
            throw new EntityNotFoundException(User_type.TYPE_NAME, user_type.getId());
        }
        return user_typeRepository.save(user_type);
    }


    public void delete(Object id) {
        User_type user_type = findById(id);
        user_typeRepository.delete(user_type);
    }
}

