package ar.com.leogaray.hiring.rest.model;

import lombok.Data;

@Data
public class UsuarioCrearRequest {

    private String username;
    private String nombre;
    private String email;
    private String password;
    private String passwordRepeat;
}
