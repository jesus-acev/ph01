package cl.ufro.dci.perfecthostapi.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.security.Key;
import java.util.Collection;

public interface JWTService {

    Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    long TOKEN_DURATION = 300000L * 4L;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER = "Authorization";
    String CLAIMS = "authorities";

    String createToken(Authentication authentication) throws IOException;

    boolean validateToken(String token);

    Claims getClaims(String token);

    String getNombreFromToken(String token);

    Collection<GrantedAuthority> getRolesFromToken(String token) throws IOException;

    String formatToken(String token);
}
