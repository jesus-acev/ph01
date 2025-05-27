package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la tabla anuncio.
 *
 * @author cristobal
 * @author victor
 */
@Repository
public interface AnuncioRepository extends CrudRepository<Anuncio, Long> {
    List<Anuncio> findAllByAnfitrionCueCorreo(String correo);
}