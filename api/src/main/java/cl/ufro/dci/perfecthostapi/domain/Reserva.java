package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Acción que puede realizar un huésped para indicar su futura estancia en un anuncio.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "reserva")
public class Reserva {

    public Reserva() {
        setResFechaCreacion(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    /**
     * ID de la reserva.
     */
    @Id
    @Column(name = "res_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resId;

    /**
     * Estado actual de la reserva, puede ser: PAGADA, CANCELADA, INICIADA, FINALIZADA.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "ers_id", referencedColumnName = "ers_id")})
    private EstadoReserva estadoReserva;

    /**
     * Huésped que realizó esta reserva.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "cue_id", referencedColumnName = "cue_id")})
    private Huesped huesped;

    /**
     * Anuncio que se está reservando.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "anu_id", referencedColumnName = "anu_id")})
    private Anuncio anuncio;

    /**
     * Precio total de la reserva, incluyendo el 10% adicional.
     */
    @Column(name = "res_precio_total", nullable = false)
    private long resPrecioTotal;

    /**
     * Cantidad de huéspedes que tiene la reserva, no afecta el precio.
     */
    @Column(name = "res_cantidad_huespedes", nullable = false, length = 10)
    private int resCantidadHuespedes;

    /**
     * Fecha en la que se creó la reserva.
     */
    @Column(name = "res_fecha_creacion")
    private String resFechaCreacion;

    /**
     * Fecha en la que inicia la estancia del huésped en el alojamiento.
     */
    @Column(name = "res_fecha_inicio", nullable = false)
    private String resFechaInicio;

    /**
     * Fecha en la que termina la estancia del huésped en el alojamiento.
     */
    @Column(name = "res_fecha_termino", nullable = false)
    private String resFechaTermino;

    /**
     * Comisiones asociadas con esta reserva.
     */
    @OneToMany(mappedBy = "reserva")
    private Set<Comision> comisiones = new HashSet<>();
}
