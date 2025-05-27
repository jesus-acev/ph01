package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.TipoCalificacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla de enums tipo_calificacion.
 *
 * @author cristobal
 * @author victor
 */
@Repository
public interface TipoCalificacionRepository extends CrudRepository<TipoCalificacion, Long> {
    /**
     * Recupera un tipo de calificación según el nombre entregado.
     *
     * @param tcaNombre Nombre del tipo de calificación.
     * @return Objeto de clase TipoCalificacion con el nombre pedido.
     */
    TipoCalificacion findByTcaNombre(String tcaNombre);
}
