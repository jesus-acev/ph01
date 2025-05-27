package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Enum que indica el tipo de una comisión, se inicia automáticamente en la base de datos.
 * Sus valores se escriben completamente en mayúsculas.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "tipo_comision")
public class TipoComision {

    /**
     * ID del tipo de comisión.
     */
    @Id
    @Column(name = "tco_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tcoId;

    /**
     * Nombre del tipo de comisión.
     */
    @Column(name = "tco_nombre", nullable = false, unique = true)
    private String tcoNombre;

    /**
     * Comisiones que son de este tipo.
     */
    @OneToMany(mappedBy = "tipoComision")
    private Set<Comision> comisiones = new HashSet<>();
}
