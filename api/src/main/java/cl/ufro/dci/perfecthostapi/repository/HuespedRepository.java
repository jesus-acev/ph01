package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.Huesped;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla cuenta, enfocado en huéspedes.
 *
 * @author cristobal
 * @author victor
 */
@Repository
@Qualifier("huespedes")
public interface HuespedRepository extends CuentaRepository<Huesped> {
}
