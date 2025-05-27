package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.Anfitrion;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla cuenta, enfocado en anfitriones.
 *
 * @author cristobal
 * @author victor
 */
@Qualifier("anfitriones")
@Repository
public interface AnfitrionRepository extends CuentaRepository<Anfitrion> {
}
