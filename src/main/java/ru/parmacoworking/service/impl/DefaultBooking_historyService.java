package ru.parmacoworking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.parmacoworking.entity.Booking_history;
import ru.parmacoworking.entity.Reservation;
import ru.parmacoworking.exceptions.EntityAlreadyExistsException;
import ru.parmacoworking.exceptions.EntityHasDetailsException;
import ru.parmacoworking.exceptions.EntityIllegalArgumentException;
import ru.parmacoworking.exceptions.EntityNotFoundException;
import ru.parmacoworking.jpa.Booking_historyRepository;
import ru.parmacoworking.jpa.ReservationRepository;

import java.util.List;


@Service
public class DefaultBooking_historyService {

    private Booking_historyRepository booking_historyRepository;

    private ReservationRepository reservationRepository;

    @Autowired
    public DefaultBooking_historyService(Booking_historyRepository booking_historyRepository, ReservationRepository reservationRepository) {
        this.booking_historyRepository = booking_historyRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Booking_history> findAll() {
        return booking_historyRepository.findAll();
    }

    public Booking_history findById(Object id) {
        Booking_history booking_history;
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

        booking_history = booking_historyRepository.findOne(parsedId);

        if (booking_history == null) {
            throw new EntityNotFoundException(Booking_history.TYPE_NAME, parsedId);
        }
        return booking_history;
    }

    public Booking_history create(Booking_history booking_history) {
        if (booking_history == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (booking_history.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Booking_history existedBooking_history = booking_historyRepository.findOne(booking_history.getId());
        if (existedBooking_history != null) {
            throw new EntityAlreadyExistsException(Booking_history.TYPE_NAME, booking_history.getId());
        }
        return booking_historyRepository.save(booking_history);
    }


    public void delete(Object id) {
        Booking_history booking_history = findById(id);
        List<Reservation> reservations = reservationRepository.findByBooking_history(booking_history);
        if (reservations.size() > 0) {
            throw new EntityHasDetailsException(Reservation.TYPE_NAME, booking_history.getId());
        }
        booking_historyRepository.delete(booking_history);
    }
}
