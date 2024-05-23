package ru.parmacoworking.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.entity.User_type;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u, ut FROM User u JOIN User_type_ref utr ON u.id = utr.user_id JOIN User_type ut ON utr.user_type_id = ut.id")
    List<Object[]> findAllUsersWithType();

    @Query("SELECT u, ut.name, jt.name FROM User u " +
            "JOIN User_type_ref utr ON u.id = utr.user_id " +
            "JOIN User_type ut ON utr.user_type_id = ut.id " +
            "JOIN User_job_title_ref ujtr ON u.id = ujtr.user_id " +
            "JOIN Job_title jt ON ujtr.job_title_id = jt.id")
    List<Object[]> findAllUsersWithTypeAndJobTitle();

    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);


}
