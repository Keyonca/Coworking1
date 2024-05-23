package ru.parmacoworking.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.parmacoworking.entity.User;

import java.util.Collection;
import java.util.Collections;


public class SecurityUser extends User implements UserDetails {

    public SecurityUser(User user) {
        super(user.getId(), user.getSurname(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(), user.getPhone_number(), user.getPhoto(), user.getForm_of_work());
    }

    public SecurityUser(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Здесь должны вернуться полномочия или роли, назначенные пользователю
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return encoder.encode(super.getPassword());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
