package cl.ufro.dci.perfecthostapi.controller.cuentas;

import cl.ufro.dci.perfecthostapi.service.ModificarCuentaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

@RequestMapping("/api")
public class ModificarCuentaController {

    private static final String CORREO_EN_USO = "El correo ya está en uso";
    private static final String NOMBRE_EN_USO = "El nombre de usuario ya está en uso";
    private static final String CUENTA_NO_EXISTE = "La cuenta no existe";
    private static final String MAXIMO_16_CARACTERES = "debe respetar el maxio de 16 caracteres en la cuenta de banco";

    @Autowired
    ModificarCuentaService modificarCuentaService;

    @Data
    private static class CuentaDTO {
        Long cueId;
        String cueNombreUsuario;
        String cueClave;
        String cueCorreo;
        String cueTelefono;
        byte[] cueFotoPerfil;
        String cueIdBanco;
    }

    @Secured(value = {"ROLE_ANFITRION", "ROLE_HUESPED"})
    @PostMapping("/modificarCuenta/{id}")
    public ResponseEntity<String> modificarCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuenta) {
        String respuesta = modificarCuentaService.modificarCuenta(id,
                cuenta.cueCorreo, cuenta.cueNombreUsuario, cuenta.cueTelefono,
                cuenta.cueFotoPerfil, cuenta.cueIdBanco);

        if (respuesta.equals(CUENTA_NO_EXISTE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        if (respuesta.equals(CORREO_EN_USO) || respuesta.equals(NOMBRE_EN_USO) ||respuesta.equals(MAXIMO_16_CARACTERES)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }
        return ResponseEntity.ok(respuesta);
    }

    @Secured(value = {"ROLE_ANFITRION", "ROLE_HUESPED"})
    @GetMapping("/eliminarCuenta/{correo}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable String correo) {
        var respuesta = modificarCuentaService.eliminarCuenta(correo);

        if (respuesta.equals(CUENTA_NO_EXISTE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        return ResponseEntity.ok(respuesta);
    }
}
