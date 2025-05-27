package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.Comision;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla comision.
 *
 * @author cristobal
 * @author victor
 */
@Repository
public interface ComisionRepository extends CrudRepository<Comision, Long> {
}
