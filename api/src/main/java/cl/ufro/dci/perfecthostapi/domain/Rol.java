package cl.ufro.dci.perfecthostapi.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Rol que puede tener cada cuenta, necesario para Spring Security.
 *
 * @author camilo
 * @version 1
 */
@Entity
@Data
@Table(name = "role")
public class Rol {

    @Id
    @Column(name = "rol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "rol_nombre")
    private String roleName;
}
