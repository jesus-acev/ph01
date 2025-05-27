package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Cuenta de anfitrión registrada en el sitio web.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("huesped")
public class Huesped extends Cuenta {

    public Huesped() {
        super();
    }

    /**
     * Calificaciones realizadas por este huésped.
     */
    @OneToMany(mappedBy = "calificador")
    private Set<Calificacion> calificaciones = new HashSet<>();

    /**
     * Reservas realizadas por el huésped.
     */
    @OneToMany(mappedBy = "huesped")
    private Set<Reserva> reservas = new HashSet<>();
}
