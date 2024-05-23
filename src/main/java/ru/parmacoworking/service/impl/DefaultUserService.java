package ru.parmacoworking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.parmacoworking.entity.Reservation;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.exceptions.EntityAlreadyExistsException;
import ru.parmacoworking.exceptions.EntityHasDetailsException;
import ru.parmacoworking.exceptions.EntityIllegalArgumentException;
import ru.parmacoworking.exceptions.EntityNotFoundException;
import ru.parmacoworking.jpa.Job_titleRepository;
import ru.parmacoworking.jpa.ReservationRepository;
import ru.parmacoworking.jpa.UserRepository;
import ru.parmacoworking.jpa.User_typeRepository;
import ru.parmacoworking.service.UserService;

import java.util.List;


@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final Job_titleRepository job_titleRepository;

    private final User_typeRepository user_typeRepository;

    private final ReservationRepository reservationRepository;

    @Autowired
    public DefaultUserService(UserRepository userRepository, Job_titleRepository job_titleRepository, User_typeRepository user_typeRepository, ReservationRepository reservationRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.job_titleRepository = job_titleRepository;
        this.user_typeRepository = user_typeRepository;
        this.reservationRepository = reservationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Object id) {
        User user;
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

        user = userRepository.findOne(parsedId);

        if (user == null) {
            throw new EntityNotFoundException(User.TYPE_NAME, parsedId);
        }
        return user;
    }

    public User create(User user) {
        if (user == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (user.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        User existedUser = userRepository.findOne(user.getId());
        if (existedUser != null) {
            throw new EntityAlreadyExistsException(User.TYPE_NAME, user.getId());
        }
        // кодируем пароль перед сохранением в базе данных
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


    @Override
    public User update(User user) {
        if (user == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (user.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        User existedUser = userRepository.findOne(user.getId());
        if (existedUser == null) {
            throw new EntityNotFoundException(User.TYPE_NAME, user.getId());
        }
        return userRepository.save(user);
    }

    public void delete(Object id) {
        User user = findById(id);
        List<Reservation> reservations = reservationRepository.findByUser(user);
        if (reservations.size() > 0) {
            throw new EntityHasDetailsException(Reservation.TYPE_NAME, user.getId());
        }
        userRepository.delete(user);
    }
}
