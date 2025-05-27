package cl.ufro.dci.perfecthostapi.controller.anuncios;

import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import cl.ufro.dci.perfecthostapi.service.ListarAnunciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>ListaController class.</p>
 *
 * @author pablo
 */
@RestController
@RequestMapping(value = "/api/anuncios/lista")
public class ListaController {

    @Autowired
    private ListarAnunciosService listarAnunciosService;

    /**
     * Método que conecta al servicio de Listar Anuncios
     *
     * @return retorna la lista de los anuncios.
     */
    @GetMapping("/listado")
    public Iterable<Anuncio> listarAnuncios(@RequestParam Long idAnfitrion) {
        return listarAnunciosService.listarAnuncios(idAnfitrion);
    }

    /**
     * Método que se encarga de actualizar los datos de un anuncio.
     *
     * @param titulo             String de titulo del anuncio.
     * @param precioPorNoche     long que corresponde valor por noche en el hospedaje.
     * @param descripcion        String con la descripción del hospedaje.
     * @param camas              entero con la cantidad de camas disponibles en el hospedaje.
     * @param capacidadHuespedes entero con la capacidad maxima permitida de huéspedes.
     * @param banios             entero con la cantidad de baños en el hospedaje.
     * @param piezas             entero con la cantidad de piezas en el hospedaje.
     * @param id                 a long.
     * @return retorna el anuncio con los datos modificados.
     */
    @GetMapping("/editar/{id}")
    public boolean edit(@RequestParam long id,
                        @RequestParam String titulo,
                        @RequestParam long precioPorNoche,
                        @RequestParam String descripcion,
                        @RequestParam int camas,
                        @RequestParam int capacidadHuespedes,
                        @RequestParam int banios,
                        @RequestParam int piezas) {

        return listarAnunciosService.editarAnuncio(id, titulo, precioPorNoche,
                descripcion, camas, capacidadHuespedes, banios, piezas);
    }

    /**
     * Método que se encarga de eliminar un anuncio.
     *
     * @param id identificador del anuncio.
     * @return retorna booleano para comprobar si se realizó la eliminación.
     */
    @GetMapping("/eliminar/{id}")
    public boolean delete(@RequestParam("id") long id) {
        return listarAnunciosService.eliminarAnuncio(id);
    }
}
