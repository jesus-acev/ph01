package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum que indica el tipo de una calificación, se inicia automáticamente en la base de datos.
 * Sus valores se escriben completamente en mayúsculas.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "tipo_calificacion")
public class TipoCalificacion {

    /**
     * ID del tipo de calificación.
     */
    @Id
    @Column(name = "tca_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tcaId;

    /**
     * Nombre del tipo de calificación.
     */
    @Column(name = "tca_nombre", nullable = false, unique = true)
    private String tcaNombre;

    /**
     * Calificaciones que son de este tipo.
     */
    @OneToMany(mappedBy = "tipoCalificacion")
    private Set<Calificacion> calificaciones = new HashSet<>();
}
