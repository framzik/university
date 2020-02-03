package ru.university.model;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_STUDENT,
    ROLE_PROFESSOR;

    @Override
    public String getAuthority() {
        return name();
    }
}