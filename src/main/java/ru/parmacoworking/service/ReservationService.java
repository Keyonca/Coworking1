package ru.parmacoworking.service;

import ru.parmacoworking.entity.Reservation;


import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();

    Reservation findById(Object id);

    Reservation create(Reservation reservation);

    void delete(Object id);
}
