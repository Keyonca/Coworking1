package ru.parmacoworking.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.parmacoworking.entity.Job_title;

import java.util.Optional;

@Repository
public interface Job_titleRepository extends JpaRepository<Job_title, Long> {

    Optional<Job_title> findById(Long id);
}
