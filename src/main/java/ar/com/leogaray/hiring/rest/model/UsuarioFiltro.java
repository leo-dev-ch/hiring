package ar.com.leogaray.hiring.rest.model;

import lombok.Data;

@Data
public class UsuarioFiltro {
    private Long id;
    private String username;
    private String nombre;
    private UsuarioEstado estado;

    public void builder(Long usuarioId, String username, String nombre, UsuarioEstado estado) {
        this.id = usuarioId;
        this.username = username;
        this.estado = estado;
        this.nombre = nombre;
    }
}
