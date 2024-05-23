package ru.parmacoworking.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.parmacoworking.entity.Booking_history;


import java.util.Optional;

@Repository
public interface Booking_historyRepository extends JpaRepository<Booking_history, Long> {

    Optional<Booking_history> findById(Long id);
}
