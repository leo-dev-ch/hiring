package ar.com.leogaray.hiring.rest.controller;

import ar.com.leogaray.hiring.configuration.security.AuthRequest;
import ar.com.leogaray.hiring.configuration.security.JwtUtilService;
import ar.com.leogaray.hiring.configuration.security.Token;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService usuarioDetailsService;
    private final JwtUtilService jwtUtilService;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authRequest.getUsername());

        final String jwt = jwtUtilService.generateToken(userDetails);

        Token token = new Token(jwt);

        return ResponseEntity.ok(token);
    }
}
