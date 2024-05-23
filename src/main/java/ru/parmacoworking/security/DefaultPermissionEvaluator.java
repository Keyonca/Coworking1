package ru.parmacoworking.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ru.parmacoworking.entity.User;

import java.io.Serializable;

public class DefaultPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object resource, Object action) {
        User user = (User) authentication.getPrincipal();
        SecurityPermission permission = new SecurityPermission(resource + "." + action);
        return user.getAuthorities().contains(permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
