package ar.com.leogaray.hiring.configuration.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String jwtToken;

}
