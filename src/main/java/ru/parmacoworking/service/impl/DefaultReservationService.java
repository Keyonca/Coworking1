package ru.parmacoworking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.parmacoworking.entity.Reservation;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.entity.Workplace;
import ru.parmacoworking.exceptions.EntityAlreadyExistsException;
import ru.parmacoworking.exceptions.EntityIllegalArgumentException;
import ru.parmacoworking.exceptions.EntityNotFoundException;
import ru.parmacoworking.jpa.*;
import ru.parmacoworking.service.ReservationService;

import java.util.List;

@Service
public class DefaultReservationService implements ReservationService {

    private final UserRepository userRepository;

    private final ReservationRepository reservationRepository;

    private final WorkplaceRepository workplaceRepository;

    @Autowired
    public DefaultReservationService(UserRepository userRepository, ReservationRepository reservationRepository, WorkplaceRepository workplaceRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.workplaceRepository = workplaceRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findById(Object id) {
        Reservation reservation;
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

        reservation = reservationRepository.findOne(parsedId);

        if (reservation == null) {
            throw new EntityNotFoundException(Reservation.TYPE_NAME, parsedId);
        }
        return reservation;
    }

    public Reservation create(Reservation reservation) {
        if (reservation == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (reservation.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Reservation existedUser = reservationRepository.findOne(reservation.getId());
        if (existedUser != null) {
            throw new EntityAlreadyExistsException(Reservation.TYPE_NAME, reservation.getId());
        }
        validateReservationData(reservation);
        return reservationRepository.save(reservation);
    }

    public void delete(Object id) {
        Reservation reservation = findById(id);
        reservationRepository.delete(reservation);
    }

    private void validateReservationData(Reservation reservation) {
        if (reservation == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }

        Object user = userRepository.findById(reservation.getUser_id()).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException(User.TYPE_NAME, reservation.getUser_id());
        }

        Object workplace = workplaceRepository.findById(reservation.getWorkplace_id()).orElse(null);
        if (workplace == null) {
            throw new EntityNotFoundException(Workplace.TYPE_NAME, reservation.getWorkplace_id());
        }

        if (reservation.getStart_date_and_time().after(reservation.getEnd_date_and_time())) {
            throw new EntityIllegalArgumentException("Дата начала бронирования должна быть перед датой окончания");
        }


    }
}

