package cl.ufro.dci.perfecthostapi.service;

import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import cl.ufro.dci.perfecthostapi.repository.AnfitrionRepository;
import cl.ufro.dci.perfecthostapi.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerAnuncioService {

    @Autowired
    AnuncioRepository anuncioRepository;
    @Autowired
    AnfitrionRepository anfitrionRepository;

    public Anuncio verAnuncio(Long idAnuncio) {
        var anuncioOptional = anuncioRepository.findById(idAnuncio);

        if (anuncioOptional.isEmpty()) {
            return null;
        }
        return anuncioOptional.get();
    }

}
