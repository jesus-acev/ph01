package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Anuncio publicado en la plataforma por un anfitrión y que puede ser reservado por un huésped.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "anuncio")
public class Anuncio {

    public Anuncio() {
        setAnuFechaCreacion(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    /**
     * ID del anuncio.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anu_id", nullable = false)
    private Long anuId;

    /**
     * Anfitrión que publicó el anuncio.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "cue_id", referencedColumnName = "cue_id")})
    private Anfitrion anfitrion;

    /**
     * Título del anuncio, corto y preciso.
     */
    @Column(name = "anu_titulo", nullable = false)
    private String anuTitulo;

    /**
     * Precio por cada noche de estadía en el lugar.
     */
    @Column(name = "anu_precio_por_noche", nullable = false)
    private long anuPrecioPorNoche;

    /**
     * Calificación promedio del anuncio.
     */
    @Column(name = "anu_calificacion_total")
    private float anuCalificacionTotal;

    /**
     * Descripción del anuncio, contiene más detalles que el título.
     */
    @Column(name = "anu_descripcion")
    private String anuDescripcion;

    /**
     * Número de camas que tiene el anuncio.
     */
    @Column(name = "anu_camas", nullable = false, length = 10)
    private int anuCamas;

    /**
     * Número máximo de huéspedes que acepta el anuncio.
     */
    @Column(name = "anu_capacidad", nullable = false)
    private int anuCapacidadHuespedes;

    /**
     * Número de baños que tiene la casa.
     */
    @Column(name = "anu_banios", nullable = false, length = 10)
    private int anuBanios;

    /**
     * Número de piezas que tiene la casa.
     */
    @Column(name = "anu_piezas", nullable = false, length = 10)
    private int anuPiezas;

    /**
     * Fecha de creación del anuncio.
     */
    @Column(name = "anu_fecha_creacion")
    private String anuFechaCreacion;

    /**
     * Fecha de inicio de disponibilidad del anuncio.
     */
    @Column(name = "anu_fecha_inicio")
    private String anuFechaInicio;

    /**
     * Fecha de finalización de disponibilidad del anuncio.
     */
    @Column(name = "anu_fecha_termino")
    private String anuFechaTermino;

    /**
     * Coordenadas del anuncio, en formato (latitud, longitud)
     */
    @Column(name = "anu_coordenadas")
    private String anuCoordenadas;

    /**
     * Reglas específicas de la casa.
     */
    @Column(name = "anu_reglas")
    private String anuReglas;

    /**
     * Servicios disponibles en la casa, como Wi-Fi, estacionamientos, gas, etc.
     */
    @Column(name = "anu_servicios")
    private String anuServicios;

    /**
     * Comisiones asociadas con este anuncio.
     */
    @ManyToMany
    @JoinTable(name = "pago_publicacion",
            joinColumns = {@JoinColumn(name = "anu_id")},
            inverseJoinColumns = {@JoinColumn(name = "com_id")})
    private Set<Comision> comisiones = new HashSet<>();

    /**
     * Reservas realizadas a este anuncio.
     */
    @OneToMany(mappedBy = "anuncio")
    private Set<Reserva> reservas = new HashSet<>();

    /**
     * Calificaciones hechas por huéspedes sobre este anuncio.
     */
    @OneToMany(mappedBy = "anuncio")
    private Set<Calificacion> calificaciones = new HashSet<>();

    /**
     * Fotos del anuncio, para mostrar en la página web.
     */
    @OneToMany(mappedBy = "anuncio")
    private Set<Imagen> imagenes = new HashSet<>();
}
