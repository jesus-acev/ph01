package cl.ufro.dci.perfecthostapi.controller.cuentas;

import cl.ufro.dci.perfecthostapi.domain.Cuenta;
import cl.ufro.dci.perfecthostapi.service.ObtenerInformacionCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>ObtenerInformacionCuentaController class.</p>
 *
 * @author camilo
 */
@Controller
@RequestMapping("/api")
public class ObtenerInformacionCuentaController {

    private static final String CUENTA_NO_EXISTE = "La cuenta no existe";

    @Autowired
    ObtenerInformacionCuentaService obtenerInformacionCuentaService;

    @GetMapping("/informacionCuenta/{correo}")
    public ResponseEntity<Cuenta> informacionCuenta(@PathVariable("correo") String correo) {
        var respuesta = obtenerInformacionCuentaService.informacionCuenta(correo);

        if (respuesta==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/recuperarContrasenia/{correo}")
    public ResponseEntity<String> recuperarContrasenia(@PathVariable String correo) {
        var respuesta = obtenerInformacionCuentaService.recuperarContrasenia(correo);

        if (respuesta.equals(CUENTA_NO_EXISTE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        return ResponseEntity.ok(respuesta);
    }

    @Secured(value = {"ROLE_ANFITRION", "ROLE_HUESPED"})
    @GetMapping("/idCuenta/{correo}")
    public ResponseEntity<String> idCuenta(@PathVariable String correo) {
        return ResponseEntity.ok(obtenerInformacionCuentaService.obtenerId(correo));
    }

}
