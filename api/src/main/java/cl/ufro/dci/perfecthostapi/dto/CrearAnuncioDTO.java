package cl.ufro.dci.perfecthostapi.dto;

import lombok.Data;

@Data
public class CrearAnuncioDTO {
    private String correo; // <-- nuevo campo
    private String titulo;
    private long precioPorNoche;
    private String descripcion;
    private int camas;
    private int capacidadHuespedes;
    private int banios;
    private int piezas;
    private String coordenadas;
}
