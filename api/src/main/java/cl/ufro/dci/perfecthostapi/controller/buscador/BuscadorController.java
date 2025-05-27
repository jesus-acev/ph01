package cl.ufro.dci.perfecthostapi.controller.buscador;

import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import cl.ufro.dci.perfecthostapi.dto.AnuncioDTO;
import cl.ufro.dci.perfecthostapi.service.BuscarAnunciosService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/buscador")
public class BuscadorController {

    @Autowired
    private BuscarAnunciosService service;

    @Data
    private static class BusquedaDTA{
        private String busqCoordenadas;
        private String busqFechaInicio;
        private String busqFechaTermino;
        private int busqHuespedes;
    }
    @Data
    private static class FechasDTA{
        private String busqFechaInicio;
        private String busqFechaTermino;
    }
    // Método utilitario privado para convertir listas
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BuscarAnunciosService buscarAnunciosService;

    @GetMapping("/user/{correo}")
    public ResponseEntity<List<AnuncioDTO>> anunciosPorCorreo(@PathVariable("correo") String correo) {
        List<AnuncioDTO> anuncios = buscarAnunciosService.anunciosPorCorreo(correo);
        if (anuncios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anuncios);
    }


    private List<AnuncioDTO> mapToDTOList(List<Anuncio> anuncios) {
        return anuncios.stream()
                .map(anuncio -> modelMapper.map(anuncio, AnuncioDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<AnuncioDTO>> listarTodosAnuncios(){
        return ResponseEntity.ok(service.listarAnuncios());
    }

    @GetMapping("/busquedaTodo")
    public ResponseEntity<List<AnuncioDTO>> buscarAnuncio(@RequestBody BusquedaDTA busquedaDTA) {
        return ResponseEntity.ok(
                mapToDTOList(
                        service.buscarxTodo(
                                busquedaDTA.getBusqCoordenadas(),
                                busquedaDTA.getBusqFechaInicio(),
                                busquedaDTA.getBusqFechaTermino(),
                                busquedaDTA.getBusqHuespedes()
                        )
                )
        );
    }

    @GetMapping("/busquedaCoord")
    public ResponseEntity<List<AnuncioDTO>> buscarAnuncioCoor(@RequestBody String coor) {
        return ResponseEntity.ok(mapToDTOList(service.buscarXCoordenadas(coor)));
    }

    @GetMapping("/busquedaFecha")
    public ResponseEntity<List<AnuncioDTO>> buscarAnuncioFecha(@RequestBody FechasDTA fechas) {
        return ResponseEntity.ok(
                mapToDTOList(
                        service.buscarXRangoFechas(fechas.getBusqFechaInicio(), fechas.getBusqFechaTermino())
                )
        );
    }

    @GetMapping("/busquedaHuesp")
    public ResponseEntity<List<AnuncioDTO>> buscarAnuncioHues(@RequestBody int huespedes) {
        return ResponseEntity.ok(mapToDTOList(service.buscarxNoHuespedes(huespedes)));
    }

    @GetMapping("/busquedaAleat")
    public ResponseEntity<List<AnuncioDTO>> aleatorio() {
        return ResponseEntity.ok(mapToDTOList(service.aleatorios20()));
    }}
