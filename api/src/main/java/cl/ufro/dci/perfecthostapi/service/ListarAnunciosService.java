package cl.ufro.dci.perfecthostapi.service;

import cl.ufro.dci.perfecthostapi.domain.Anfitrion;
import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import cl.ufro.dci.perfecthostapi.repository.AnfitrionRepository;
import cl.ufro.dci.perfecthostapi.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListarAnunciosService {

    @Autowired
    AnuncioRepository anuncioRepository;
    @Autowired
    AnfitrionRepository anfitrionRepository;

    public Iterable<Anuncio> listarAnuncios(Long idAnfitrion) {
        Optional<Anfitrion> anfitrionOptional = anfitrionRepository.findById(idAnfitrion);
        if (anfitrionOptional.isEmpty()) {
            return null;
        } else {
            try {
                var anfitrion = anfitrionOptional.get();
                return anfitrion.getAnuncios();
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("No se encuentra el listado");
            }
        }
    }

    public boolean editarAnuncio(Long idAnuncio, String titulo, Long precioPorNoche,
                                 String descripcion, int camas, int capacidadHuespedes,
                                 int banios, int piezas) {
        Optional<Anuncio> anuncioOptional = anuncioRepository.findById(idAnuncio);
        if (anuncioOptional.isEmpty() || titulo.isEmpty() || precioPorNoche == 0 ||
                descripcion.isEmpty() || camas == 0 || capacidadHuespedes == 0 ||
                banios == 0 || piezas == 0) {
            return false;
        } else {
            var anuncio = anuncioOptional.get();
            anuncio.setAnuTitulo(titulo);
            anuncio.setAnuPrecioPorNoche(precioPorNoche);
            anuncio.setAnuDescripcion(descripcion);
            anuncio.setAnuCamas(camas);
            anuncio.setAnuCapacidadHuespedes(capacidadHuespedes);
            anuncio.setAnuBanios(banios);
            anuncio.setAnuPiezas(piezas);
            anuncioRepository.save(anuncio);
            return true;
        }

    }

    public boolean eliminarAnuncio(Long idAnuncio) {
        if (anuncioRepository.existsById(idAnuncio)) {
            anuncioRepository.deleteById(idAnuncio);
            return true;
        } else {
            return false;
        }

    }

}
