package ru.parmacoworking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.exceptions.EntityIllegalArgumentException;
import ru.parmacoworking.exceptions.EntityNotFoundException;
import ru.parmacoworking.jpa.UserRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SecurityUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    @Autowired
    public SecurityUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails userDetails) {

    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            throw new AccessDeniedException("Не удается изменить пароль, так как пользователь не аутентифицирован.");
        }

        String username = currentUser.getName();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с именем " + username + " не найден"));
        if (user == null) {
            throw new EntityNotFoundException("Пользователь не найден.");
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new EntityIllegalArgumentException("Неверный старый пароль.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean userExists(String username) {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Object[]> usersWithType = userRepository.findAllUsersWithType();

        for (Object[] result : usersWithType) {
            User user = (User) result[0];
            String userType = (String) result[1];

            if (username.equals(user.getEmail())) {
                List<SecurityPermission> permissions = new ArrayList<>();
                if ("Пользователь".equals(userType)) {
                    permissions.add(new SecurityPermission("user.read"));
                    permissions.add(new SecurityPermission("user.update"));
                    permissions.add(new SecurityPermission("reservation.read"));
                    permissions.add(new SecurityPermission("reservation.create"));
                } else if ("HR-менеджер".equals(userType)) {
                    permissions.add(new SecurityPermission("user.read"));
                    permissions.add(new SecurityPermission("user.update"));
                    permissions.add(new SecurityPermission("user.delete"));
                    permissions.add(new SecurityPermission("reservation.read"));
                    permissions.add(new SecurityPermission("reservation.create"));
                    permissions.add(new SecurityPermission("reservation.delete"));
                    permissions.add(new SecurityPermission("job_title.read"));
                    permissions.add(new SecurityPermission("job_title.create"));
                    permissions.add(new SecurityPermission("job_title.delete"));
                    permissions.add(new SecurityPermission("job_title.update"));
                    permissions.add(new SecurityPermission("workplace.read"));
                    permissions.add(new SecurityPermission("workplace.create"));
                    permissions.add(new SecurityPermission("workplace.update"));
                    permissions.add(new SecurityPermission("workplace.delete"));
                    permissions.add(new SecurityPermission("user_type.read"));
                    permissions.add(new SecurityPermission("user_type.create"));
                    permissions.add(new SecurityPermission("user_type.update"));
                    permissions.add(new SecurityPermission("user_type.delete"));
                    permissions.add(new SecurityPermission("booking_history.read"));
                    permissions.add(new SecurityPermission("booking_history.create"));
                    permissions.add(new SecurityPermission("booking_history.delete"));
                }

                return new SecurityUser(user.getEmail(), user.getPassword(), permissions);
            }
        }

        throw new UsernameNotFoundException("Пользователь с именем " + username + " не найден");
    }
}
