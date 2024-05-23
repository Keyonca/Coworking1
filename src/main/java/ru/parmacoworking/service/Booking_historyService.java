package ru.parmacoworking.service;

import ru.parmacoworking.entity.Booking_history;

import java.util.List;

public interface Booking_historyService {

    List<Booking_history> findAll();

    Booking_history findById(Object id);

    Booking_history create(Booking_history booking_history);

    void delete(Object id);
}
