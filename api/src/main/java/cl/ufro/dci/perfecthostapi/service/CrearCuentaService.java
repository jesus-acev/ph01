package cl.ufro.dci.perfecthostapi.service;

import cl.ufro.dci.perfecthostapi.domain.Anfitrion;
import cl.ufro.dci.perfecthostapi.domain.Cuenta;
import cl.ufro.dci.perfecthostapi.domain.Huesped;
import cl.ufro.dci.perfecthostapi.domain.Rol;
import cl.ufro.dci.perfecthostapi.repository.AnfitrionRepository;
import cl.ufro.dci.perfecthostapi.repository.CuentaRepository;
import cl.ufro.dci.perfecthostapi.repository.HuespedRepository;
import cl.ufro.dci.perfecthostapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * <p>CrearCuentaService class.</p>
 *
 * @author Camilo
 * @version 1
 */
@Service
public class CrearCuentaService {

    private static final String CORREO_EN_USO = "El correo ya está en uso";
    private static final String NOMBRE_EN_USO = "El nombre de usuario ya está en uso";

    @Autowired
    CuentaRepository<Cuenta> cuentaRepository;

    @Autowired
    AnfitrionRepository anfitrionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    HuespedRepository huespedRepository;

    public String crearCuentaAnfitrion(String nombreUsuario, String clave, String correo) {
        var anfitrion = new Anfitrion();
        Set<Rol> roles = new HashSet<>();

        if (cuentaRepository.existsByCueCorreo(correo)) {
            return CORREO_EN_USO;
        }
        if (cuentaRepository.existsByCueNombreUsuario(nombreUsuario)) {
            return NOMBRE_EN_USO;
        }

        Optional<Rol> optionalRol = Optional.ofNullable(roleRepository.findByRoleName("ROLE_ANFITRION"));
        if (!optionalRol.isPresent()) {
            throw new RuntimeException("No se encontró el rol ROLE_ANFITRION");
        }
        Rol rol = optionalRol.get();
        roles.add(rol);
        anfitrion.setCueClave(clave);
        anfitrion.setCueCorreo(correo);
        anfitrion.setCueNombreUsuario(nombreUsuario);
        anfitrion.setRoles(roles);
        anfitrionRepository.save(anfitrion);

        return "El anfitrión ha sido creada exitosamente";
    }

    public String crearCuentaHuesped(String nombreUsuario, String clave, String correo) {
        var huesped = new Huesped();
        Set<Rol> roles = new HashSet<>();

        if (cuentaRepository.existsByCueCorreo(correo)) {
            return CORREO_EN_USO;
        }
        if (cuentaRepository.existsByCueNombreUsuario(nombreUsuario)) {
            return NOMBRE_EN_USO;
        }

        Optional<Rol> optionalRol = Optional.ofNullable(roleRepository.findByRoleName("ROLE_HUESPED"));
        if (!optionalRol.isPresent()) {
            throw new RuntimeException("No se encontró el rol ROLE_HUESPED");
        }
        Rol rol = optionalRol.get();
        roles.add(rol);

        huesped.setCueClave(clave);
        huesped.setCueCorreo(correo);
        huesped.setCueNombreUsuario(nombreUsuario);
        huesped.setRoles(roles);

        huespedRepository.save(huesped);
        return "El huésped ha sido creado exitosamente";
    }

}

