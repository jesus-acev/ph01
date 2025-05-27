package cl.ufro.dci.perfecthostapi.service;

import cl.ufro.dci.perfecthostapi.domain.Cuenta;
import cl.ufro.dci.perfecthostapi.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModificarCuentaService {

    private static final String CORREO_EN_USO = "El correo ya está en uso";
    private static final String NOMBRE_EN_USO = "El nombre de usuario ya está en uso";
    private static final String CUENTA_NO_EXISTE = "La cuenta no existe";
    private static final String MAXIMO_16_CARACTERES = "debe respetar el maxio de 16 caracteres en la cuenta de banco";

    @Autowired
    CuentaRepository<Cuenta> cuentaRepository;

    public String modificarCuenta(Long id, String correo, String nombre, String telefono, byte[] foto, String cuentaBancaria) {

        var cuentaOptional = cuentaRepository.findById(id);

        if (cuentaOptional.isEmpty()) {
            return CUENTA_NO_EXISTE;
        }

        var cuenta = cuentaOptional.get();

        if (cuentaRepository.existsByCueCorreo(correo) && !correo.equals(cuenta.getCueCorreo())) {
            return CORREO_EN_USO;
        }
        if (cuentaRepository.existsByCueNombreUsuario(nombre) && !nombre.equals(cuenta.getCueNombreUsuario())) {
            return NOMBRE_EN_USO;
        }
        if (cuentaBancaria.length()>16){
            return MAXIMO_16_CARACTERES;
        }

        cuenta.setCueNombreUsuario(nombre);
        cuenta.setCueCorreo(correo);
        cuenta.setCueTelefono(telefono);
        cuenta.setCueFotoPerfil(foto);
        cuenta.setCueIdBanco(cuentaBancaria);

        cuentaRepository.save(cuenta);

        return "La cuenta fue actualizada";
    }

    public String eliminarCuenta(String correo) {
        var cuenta = cuentaRepository.findByCueCorreo(correo);
        if (cuenta == null) {
            return CUENTA_NO_EXISTE;
        }
        cuentaRepository.deleteById(cuenta.getCueId());
        return "Cuenta eliminada satisfactoriamente";
    }

}
