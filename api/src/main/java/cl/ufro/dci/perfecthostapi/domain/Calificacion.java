package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Calificación realizada por un huésped después de finalizar un alojamiento.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "calificacion")
public class Calificacion {

    public Calificacion() {
        setCalFechaCreacion(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    /**
     * ID de la calificación.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cal_id", nullable = false)
    private Long calId;

    /**
     * Tipo de la calificación, puede ser: ALOJAMIENTO.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "tca_id", referencedColumnName = "tca_id")})
    private TipoCalificacion tipoCalificacion;

    /**
     * Huésped que hizo la calificación.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "cue_id", referencedColumnName = "cue_id")})
    private Huesped calificador;

    /**
     * Anuncio calificado.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "anu_id", referencedColumnName = "anu_id")})
    private Anuncio anuncio;

    /**
     * Título de la calificación.
     */
    @Column(name = "cal_titulo", nullable = false)
    private String calTitulo;

    /**
     * Comentario con informaciicional sobre la calificación.
     */
    @Column(name = "cal_comentario")
    private String calComentario;

    /**
     * Puntaje de la calificación, en estrellas, entre 1 y 5.
     */
    @Column(name = "cal_puntaje", nullable = false)
    private int calPuntaje;

    /**
     * Fecha en la que se realizó la calificación.
     */
    @Column(name = "cal_fecha_creacion")
    private String calFechaCreacion;
}
