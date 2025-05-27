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
@DiscriminatorValue("anfitrion")
public class Anfitrion extends Cuenta {

    public Anfitrion() {
        super();
    }

    /**
     * Calificación promedio del anfitrión.
     */
    @Column(name = "anf_calificacion_media")
    private float anfCalificacionMedia;

    /**
     * Empresa a la cual pertenece el anfitrión.
     */
    @Column(name = "anf_empresa")
    private String anfEmpresa;

    /**
     * Anuncios publicados por el anfitrión.
     */
    @OneToMany(mappedBy = "anfitrion")
    private Set<Anuncio> anuncios = new HashSet<>();
}
