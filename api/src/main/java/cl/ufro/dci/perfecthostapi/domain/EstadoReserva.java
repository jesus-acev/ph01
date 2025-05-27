package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum que indica el estado de una reserva, se inicia automáticamente en la base de datos.
 * Sus valores se escriben completamente en mayúsculas.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "estado_reserva")
public class EstadoReserva {

    /**
     * ID del estado de reserva.
     */
    @Id
    @Column(name = "ers_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ersId;

    /**
     * Nombre del estado de reserva.
     */
    @Column(name = "ers_nombre", nullable = false, unique = true)
    private String ersNombre;

    /**
     * Reservas que están en este estado.
     */
    @OneToMany(mappedBy = "estadoReserva")
    private Set<Reserva> reservas = new HashSet<>();
}
