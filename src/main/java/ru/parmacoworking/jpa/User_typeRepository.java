package ru.parmacoworking.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.parmacoworking.entity.User_type;

import java.util.Optional;

@Repository
public interface User_typeRepository extends JpaRepository<User_type, Long> {

    Optional<User_type> findById(Long id);
}
