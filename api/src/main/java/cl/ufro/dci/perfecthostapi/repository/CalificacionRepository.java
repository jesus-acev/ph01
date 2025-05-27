package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.Calificacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla calificacion.
 *
 * @author cristobal
 * @author victor
 */
@Repository
public interface CalificacionRepository extends CrudRepository<Calificacion, Long> {
}
