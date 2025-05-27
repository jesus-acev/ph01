package cl.ufro.dci.perfecthostapi.controller.cuentas;

import cl.ufro.dci.perfecthostapi.service.CrearCuentaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>CrearCuentaController class.</p>
 *
 * @author camilo
 */
@Controller
@RequestMapping("/api")
public class CrearCuentaController {

    private static final String CORREO_EN_USO = "El correo ya está en uso";
    private static final String NOMBRE_EN_USO = "El nombre de usuario ya está en uso";
    private static final String ESPACIO_EN_BLANCO = "No debe dejar ninguna información vacía";

    @Autowired
    CrearCuentaService crearCuentaService;

    @Data
    private static class CuentaDTO {
        String cueNombreUsuario;
        String cueClave;
        String cueCorreo;
    }

    @PostMapping("/registrarseAnfitrion")
    public ResponseEntity<String> registrarseAnfitrion(@RequestBody CuentaDTO anfitrion) {
        if (!anfitrion.cueNombreUsuario.equals("") && !anfitrion.cueCorreo.equals("")
                && !anfitrion.cueClave.equals("") ) {
            String respuesta = crearCuentaService.crearCuentaAnfitrion(anfitrion.cueNombreUsuario,
                    anfitrion.cueClave, anfitrion.cueCorreo);

            if (respuesta.equals(CORREO_EN_USO) || respuesta.equals(NOMBRE_EN_USO)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
            }
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ESPACIO_EN_BLANCO);
        }
    }

    @PostMapping("/registrarseHuesped")
    public ResponseEntity<String> registrarseHuesped(@RequestBody CuentaDTO huesped) {

        if (!huesped.cueNombreUsuario.equals("") && !huesped.cueCorreo.equals("")
                && !huesped.cueClave.equals("") ) {
            String respuesta = crearCuentaService.crearCuentaHuesped(huesped.cueNombreUsuario,
                    huesped.cueClave, huesped.cueCorreo);

            if (respuesta.equals(CORREO_EN_USO) || respuesta.equals(NOMBRE_EN_USO)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
            }
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ESPACIO_EN_BLANCO);
        }
    }
}
