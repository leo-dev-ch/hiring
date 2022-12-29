package ar.com.leogaray.hiring;

import ar.com.leogaray.hiring.rest.converter.UsuarioConverter;
import ar.com.leogaray.hiring.rest.model.Rol;
import ar.com.leogaray.hiring.rest.model.UsuarioCrearRequest;
import ar.com.leogaray.hiring.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HiringApplicationBootStrap implements CommandLineRunner {
    private final UsuarioService usuarioService;
    private final UsuarioConverter usuarioConverter;

    @Override
    public void run(String... args) throws Exception {
        UsuarioCrearRequest admin = new UsuarioCrearRequest();
        admin.setEmail("admin@admin.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setPasswordRepeat("admin");
        admin.setNombre("admin");
        admin.setRol(Rol.ADMIN);
        admin.setNombre("admin");

        usuarioService.crearUsuario(usuarioConverter.toEntity(admin));
        UsuarioCrearRequest user = new UsuarioCrearRequest();
        user.setEmail("user@user.com");
        user.setUsername("user");
        user.setPassword("user");
        user.setPasswordRepeat("user");
        user.setNombre("user");
        user.setRol(Rol.USER);
        user.setNombre("user");

        usuarioService.crearUsuario(usuarioConverter.toEntity(user));
    }
}
