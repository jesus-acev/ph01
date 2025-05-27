package cl.ufro.dci.perfecthostapi.controller.anuncios;

import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import cl.ufro.dci.perfecthostapi.dto.AnuncioDTO;
import cl.ufro.dci.perfecthostapi.service.VerAnuncioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>VerController class.</p>
 *
 * @author pablo
 */
@RestController
@RequestMapping(value = "/api/anuncios/ver")
public class VerController {

    @Autowired
    private VerAnuncioService verAnuncioService;

     /**
     * <p>search.</p>
     *
     * @param id a long.
     * @return a {@link cl.ufro.dci.perfecthostapi.domain.Anuncio} object.
     */

     @Autowired
     private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AnuncioDTO> mostrarAnuncio(@PathVariable("id") long id) {
        Anuncio anuncio = verAnuncioService.verAnuncio(id);
        if (anuncio == null) {
            return ResponseEntity.notFound().build();
        }
        AnuncioDTO dto = modelMapper.map(anuncio, AnuncioDTO.class);
        System.out.println(dto);
        return ResponseEntity.ok(dto);
    }
    /**
     * <p>coordenadas.</p>
     *
     * @param coordenada an array of {@link double} objects.
     * @return a {@link java.lang.String} object.
     */
    /*@PutMapping("/geolocalizacion/{coordenada}")
    public String coordenadas(@RequestParam("coordenada") double[] coordenada) {
        double lat = coordenada[0];
        double log = coordenada[1];
        var latitud = String.valueOf(lat);
        var longitud = String.valueOf(log);
        var ubicacion = new MapaService();
        return ubicacion.geolocation(latitud, longitud);
    }*/
}
