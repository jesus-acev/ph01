package cl.ufro.dci.perfecthostapi;

import cl.ufro.dci.perfecthostapi.auth.filter.JWTAuthenticationFilter;
import cl.ufro.dci.perfecthostapi.auth.filter.JWTAuthorizationFilter;
import cl.ufro.dci.perfecthostapi.auth.service.CustomUserDetailsService;
import cl.ufro.dci.perfecthostapi.auth.service.JWTService;
import cl.ufro.dci.perfecthostapi.domain.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTService jwtService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    /**
     * It allows to assign username, password (it store encrypted) and user roles.
     * Permite asignar nombre de usuario, contraseña (se almacenada cifrada) y roles a los usuarios.
     *
     * @param authenticationManagerBuilder It allows to construct the authentication in memory.
     *                                     Permite construir fácilmente en la autenticación de memoria, basada en JDBC.
     */
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.customUserDetailsService).
                passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    /**
     * It allows to assign the access to specific views for the non-registered users and set the configuration about login and logout.
     * Permite asignar el acceso a vistas específicas para los usuarios no registrados, configurar el login y logout.
     *
     * @param http It allows configurate the security based in web for specific http requests.
     *             Permite configurar la seguridad basada en web para solicitudes http específicas.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().
                and().
                headers().frameOptions().disable().
                and().
                authorizeRequests().
                antMatchers("/", "/api/registrarseAnfitrion", "/api/registrarseHuesped", "/api/recuperarContrasenia/**", "/api/informacionCuenta/**","/api/buscador/**").permitAll().
                anyRequest().authenticated().
                and().
                addFilter(new JWTAuthenticationFilter(authenticationManager(), this.jwtService)).
                addFilter(new JWTAuthorizationFilter(authenticationManager(), this.jwtService)).
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * It allows to encrypt a password using BCrypt.
     * Permite cifrar una clave utilizando BCrypt.
     *
     * @param password Passowrd to encrypt.
     *                 Clave a cifrar.
     * @return Encypted passowrd. Clave cifrada.
     */
    public String encryptPassword(String password) {
        return passwordEncoder().encode(password);
    }

    /**
     * It validates the actual password of a user.
     * Valida la contraseña actual de un usuario.
     *
     * @param actualPassowrd Actual password.
     *                       Contraseña actual.
     * @param actualUser     Actual user.
     *                       Usuario actual.
     * @return Boolean that indicates if the password is valid.
     */
    public boolean validatePassword(String actualPassowrd, Cuenta actualUser) {
        return (new BCryptPasswordEncoder()).matches(actualPassowrd, actualUser.getCueClave());
    }
}


