package ar.com.leogaray.hiring.configuration.security;

import ar.com.leogaray.hiring.model.UsuarioEntity;
import ar.com.leogaray.hiring.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
        return User.withUsername(username).password(usuario.getPassword()).roles(usuario.getRol().getAuthority()).build();
    }
}
