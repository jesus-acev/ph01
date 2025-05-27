package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Una comisión representa los detalles de toda transacción realizada en la aplicación.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "comision")
public class Comision {

    public Comision() {
        setComFechaCreacion(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    /**
     * ID de la comisión.
     */
    @Id
    @Column(name = "com_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comId;

    /**
     * Tipo de comisión, puede ser: PUBLICACION, ACTUALIZACION, RESERVA, CANCELACION, PAGO.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "tco_id", referencedColumnName = "tco_id")})
    private TipoComision tipoComision;

    /**
     * Cuenta de PerfectHost asociada con esta comisión.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "cue_id", referencedColumnName = "cue_id")})
    private Cuenta deudor;

    /**
     * Reserva asociada con esta comisión.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "res_id", referencedColumnName = "res_id")})
    private Reserva reserva;

    /**
     * ID de la transacción, generado aleatoriamente.
     */
    @Column(name = "com_id_transaccion", unique = true)
    private String comIdTransaccion;

    /**
     * Valor total de la comisión.
     */
    @Column(name = "com_valor_total", nullable = false)
    private long comValor;

    /**
     * Fecha en la que se realizó la comisión.
     */
    @Column(name = "com_fecha_creacion")
    private String comFechaCreacion;

    /**
     * Anuncios asociados con esta comisión.
     */
    @ManyToMany(mappedBy = "comisiones")
    private Set<Anuncio> anuncios = new HashSet<>();

}
