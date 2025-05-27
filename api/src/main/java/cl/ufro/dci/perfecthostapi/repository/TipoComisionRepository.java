package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.TipoComision;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla de enums tipo_comision.
 *
 * @author cristobal
 * @author victor
 */
@Repository
public interface TipoComisionRepository extends CrudRepository<TipoComision, Long> {
    /**
     * Recupera un tipo de comisión según el nombre entregado.
     *
     * @param tcoNombre Nombre del tipo de comisión.
     * @return Objeto de clase TipoComision con el nombre pedido.
     */
    TipoComision findByTcoNombre(String tcoNombre);
}
