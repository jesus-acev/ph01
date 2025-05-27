package cl.ufro.dci.perfecthostapi.service;

import cl.ufro.dci.perfecthostapi.domain.Anfitrion;
import cl.ufro.dci.perfecthostapi.domain.Cuenta;
import cl.ufro.dci.perfecthostapi.repository.CuentaRepository;
import cl.ufro.dci.perfecthostapi.util.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ObtenerInformacionCuentaService {

    private static final String CUENTA_NO_EXISTE = "La cuenta no existe";

    @Autowired
    CuentaRepository<Cuenta> cuentaRepository;
    @Autowired
    EnvioEmailService envioEmailService;

    public Cuenta informacionCuenta(String correo) {
        var cuenta = cuentaRepository.findByCueCorreo(correo);
        var aux = new Anfitrion();

        if (cuenta == null) {
            return null;
        }
        aux.setCueCorreo(cuenta.getCueCorreo());
        aux.setCueFotoPerfil(cuenta.getCueFotoPerfil());
        aux.setCueNombreUsuario(cuenta.getCueNombreUsuario());
        aux.setCueTelefono(cuenta.getCueTelefono());
        aux.setCueIdBanco(cuenta.getCueIdBanco());
        aux.setRoles(cuenta.getRoles());

        return aux;
    }

    public String recuperarContrasenia(String correo) {
        var cuenta = cuentaRepository.findByCueCorreo(correo);
        if (cuenta == null) {
            return CUENTA_NO_EXISTE;
        }
        envioEmailService.sendEmail(correo, "Solicitud de recuperacion de contraseña", "Su contraseña actual es la siguiente:  " + cuenta.getCueClave());
        return "Se ha enviado la contraseña a su correo";
    }

    public String obtenerId(String correo) {
        var cuenta = cuentaRepository.findByCueCorreo(correo);
        if (cuenta == null) {
            return null;
        }
        return cuenta.getCueId().toString();
    }
}
