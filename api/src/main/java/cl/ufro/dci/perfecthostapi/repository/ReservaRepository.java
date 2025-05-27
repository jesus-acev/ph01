package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla reserva.
 *
 * @author cristobal
 * @author victor
 */
@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
}
