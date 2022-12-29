package ar.com.leogaray.hiring.model;

import org.springframework.security.core.GrantedAuthority;

public enum Rol implements GrantedAuthority {

    ADMIN, USER, PUBLIC;

    public String getAuthority() {
        return name();
    }

}