package ru.parmacoworking.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.parmacoworking.entity.Booking_history;
import ru.parmacoworking.entity.Reservation;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.entity.Workplace;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);

    List<Reservation> findByWorkplace(Workplace workplace);

    @Query("SELECT r, u, w FROM Reservation r INNER JOIN User u ON r.user_id = u.id INNER JOIN Workplace w ON r.workplace_id = w.id")
    List<Object[]> findReservationWithUserAndWorkplace();

    Optional<Reservation> findById(Long id);

    List<Reservation> findByBooking_history(Booking_history booking_history);
}
