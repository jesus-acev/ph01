package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase abstracta que representa una cuenta registrada en PerfectHost.
 *
 * @author cristobal
 * @author victor
 * @version 2
 */
@Entity
@Data
@Table(name = "cuenta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cue_discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Cuenta")
public abstract class Cuenta {

    protected Cuenta() {
        setCueFechaCreacion(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    /**
     * ID de la cuenta.
     */
    @Id
    @Column(name = "cue_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long cueId;

    /**
     * Nombre de usuario de la cuenta, no necesita ser el nombre real.
     */
    @Column(name = "cue_nombre_usuario", nullable = false, unique = true)
    protected String cueNombreUsuario;

    /**
     * Contraseña con la que se ingresa al sitio, mínimo 8 caracteres.
     */
    @Column(name = "cue_clave", nullable = false)
    @Size(min = 8)
    protected String cueClave;

    /**
     * Foto de perfil que identifica al usuario.
     */
    @Column(name = "cue_foto_perfil")
    protected byte[] cueFotoPerfil;

    /**
     * Teléfono de contacto de la cuenta.
     */
    @Column(name = "cue_telefono")
    @Size(max = 14)
    protected String cueTelefono;

    /**
     * Correo con el que se registró la cuenta.
     */
    @Column(name = "cue_correo", nullable = false, unique = true)
    @Email
    protected String cueCorreo;

    /**
     * ID de la cuenta bancaria asociada con esta cuenta de PerfectHost.
     */
    @Column(name = "cue_id_banco", unique = true)
    @Size(max = 16)
    protected String cueIdBanco;

    /**
     * Saldo de la cuenta bancaria asociada.
     */
    @Column(name = "cue_saldo")
    protected long cueSaldo;

    /**
     * Fecha de creación de la cuenta.
     */
    @Column(name = "cue_fecha_creacion")
    protected String cueFechaCreacion;

    /**
     * Comisiones en las que participó esta cuenta.
     */
    @OneToMany(mappedBy = "deudor")
    protected Set<Comision> comisiones = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "permisos",
            joinColumns = @JoinColumn(name = "cue_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    protected Set<Rol> roles;
}
