package cl.ufro.dci.perfecthostapi.repository;

import cl.ufro.dci.perfecthostapi.domain.EstadoReserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la tabla de enums estado_reserva.
 *
 * @author cristobal
 * @author victor
 */
@Repository
public interface EstadoReservaRepository extends CrudRepository<EstadoReserva, Long> {
    /**
     * Recupera un estado de reserva según el nombre entregado.
     *
     * @param ersNombre Nombre del estado de reserva.
     * @return Objeto de clase EstadoReserva con el nombre pedido.
     */
    EstadoReserva findByErsNombre(String ersNombre);
}
