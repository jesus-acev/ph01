package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Cada imagen que se muestra de un anuncio, se guarda como una rreglo de bytes para hacerlo compatible con la base de datos.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "imagen")
public class Imagen {

    /**
     * ID de la imagen.
     */
    @Id
    @Column(name = "img_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    /**
     * Anuncio al que pertenece esta imagen.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {@JoinColumn(name = "anu_id", referencedColumnName = "anu_id")})
    private Anuncio anuncio;

    /**
     * Arreglo de bytes que contiene la imagen.
     */
    @Column(name = "img_imagen", nullable = false)
    private byte[] imgImagen;
}
