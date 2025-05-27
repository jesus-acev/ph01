package cl.ufro.dci.perfecthostapi.controller.anuncios;

import cl.ufro.dci.perfecthostapi.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * <p>PdfController class.</p>
 *
 * @author pablo
 */
@RestController
@RequestMapping(value = "/api/anuncios/crear")
public class PdfController {

    @Autowired
    private AnuncioRepository anuncioRepository;

    /**
     * Método que se encarga de llamar a la clase GeneratePdfController
     * que genera un archivo .pdf con el Formulario de un anuncio en específico, que es consultado
     * con un parámetro al repositorio de anuncios.
     *
     * @param id variable de tipo long que corresponde al identificador único de un anuncio.
     */
    @PostMapping(path = "/generarPdf/{id}")
    public void generatePdf(@RequestParam long id) {
        try {
            var anuncioOptional = anuncioRepository.findById(id);

            if (anuncioOptional.isPresent()) {
                var anuncio = anuncioOptional.get();
                var pdf = new GeneratePdfController();
                pdf.generate(anuncio);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
