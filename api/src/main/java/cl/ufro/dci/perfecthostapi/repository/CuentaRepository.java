package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.Cuenta;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio genérico para la tabla cuenta.
 *
 * @author cristobal
 * @author victor
 */
@Repository
@Qualifier("Cuenta")
public interface CuentaRepository<T extends Cuenta> extends CrudRepository<T, Long> {
    /**
     * Recupera una cuenta (huésped o anfitrión) según el nombre entregado.
     *
     * @param cueNombreUsuario Nombre de usuario de la cuenta.
     * @return Objeto de clase Cuenta con el nombre pedido.
     */
    Cuenta findByCueNombreUsuario(String cueNombreUsuario);

    default boolean existsByCueNombreUsuario(String cueNombreUsuario) {
        return findByCueNombreUsuario(cueNombreUsuario) != null;
    }

    /**
     * Recupera una cuenta (huésped o anfitrión) según el correo entregado.
     *
     * @param cueCorreo Correo de la cuenta de usuario.
     * @return Objeto de clase Cuenta con el nombre pedido.
     */
    Cuenta findByCueCorreo(String cueCorreo);

    default boolean existsByCueCorreo(String cueCurreo) {
        return findByCueCorreo(cueCurreo) != null;
    }
}
