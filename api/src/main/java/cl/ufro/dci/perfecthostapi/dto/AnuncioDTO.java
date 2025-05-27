package cl.ufro.dci.perfecthostapi.dto;

import lombok.Data;

@Data
public class AnuncioDTO {
    private Long id;
    private String titulo;
    private long precioPorNoche;
    private float calificacionTotal;
    private String descripcion;
    private int camas;
    private int capacidadHuespedes;
    private int banios;
    private int piezas;
    private String fechaCreacion;
    private String fechaInicio;
    private String fechaTermino;
    private String coordenadas;
    private String reglas;
    private String servicios;
}