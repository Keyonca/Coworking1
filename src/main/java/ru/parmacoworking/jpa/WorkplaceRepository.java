package ru.parmacoworking.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.parmacoworking.entity.Workplace;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

    @Query("SELECT wp, bh FROM Workplace wp JOIN Booking_history_ref bhr ON wp.id = bhr.workplace_id " +
            "JOIN Booking_history bh ON bhr.booking_history_id = bh.id")
    List<Object[]> findAllWorkplacesWithBookingHistory();

    Optional<Workplace> findById(Long id);
}
