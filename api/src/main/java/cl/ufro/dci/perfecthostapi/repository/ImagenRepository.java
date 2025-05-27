package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.Imagen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla imagen.
 *
 * @author cristobal
 * @author victor
 */
@Repository
public interface ImagenRepository extends CrudRepository<Imagen, Long> {
}
