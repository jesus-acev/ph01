package cl.ufro.dci.perfecthostapi.controller.anuncios;

import cl.ufro.dci.perfecthostapi.dto.CrearAnuncioDTO;
import cl.ufro.dci.perfecthostapi.service.CrearAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>CrearController class.</p>
 *
 * @author pablo
 */
@RestController
@RequestMapping(value = "/api/anuncios/crear")
public class CrearController {

    @Autowired
    private CrearAnuncioService crearAnuncioService;

    /**
     * Método encargado de guardar los nuevos anuncios que vaya creando un anfitrión.
     *
     * @param idAnfitrion
     * @param titulo             String de titulo del anuncio.
     * @param coordenada         arreglo double de las coordenadas para la geolocalización del hospedaje.
     * @param precioPorNoche     long que corresponde valor por noche en el hospedaje.
     * @param descripcion        String con la descripción del hospedaje.
     * @param camas              entero con la cantidad de camas disponibles en el hospedaje.
     * @param capacidadHuespedes entero con la capacidad maxima permitida de huéspedes.
     * @param banios             entero con la cantidad de baños en el hospedaje.
     * @param piezas             entero con la cantidad de piezas en el hospedaje.
     * @return Retorna un booleano.
     */
    @PostMapping("/guardarFormulario")
    public boolean guardar(@RequestBody CrearAnuncioDTO crearAnuncioDTO) {
        return crearAnuncioService.crearAnuncio(crearAnuncioDTO);
    }
}
