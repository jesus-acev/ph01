package cl.ufro.dci.perfecthostapi.service;

import cl.ufro.dci.perfecthostapi.domain.Anfitrion;
import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import cl.ufro.dci.perfecthostapi.domain.Cuenta;
import cl.ufro.dci.perfecthostapi.dto.CrearAnuncioDTO;
import cl.ufro.dci.perfecthostapi.repository.AnfitrionRepository;
import cl.ufro.dci.perfecthostapi.repository.AnuncioRepository;
import cl.ufro.dci.perfecthostapi.repository.CuentaRepository;
import cl.ufro.dci.perfecthostapi.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CrearAnuncioService {

    @Autowired
    AnuncioRepository anuncioRepository;
    @Autowired
    AnfitrionRepository anfitrionRepository;
    @Autowired
    ImagenRepository imagenRepository;
    @Autowired
    private ObtenerInformacionCuentaService obtenerInformacionCuentaService;

    public boolean crearAnuncio(CrearAnuncioDTO dto) {
        if (dto.getCorreo() == null || dto.getCorreo().isEmpty() ||
                dto.getTitulo() == null || dto.getTitulo().isEmpty() ||
                dto.getPrecioPorNoche() == 0 || dto.getDescripcion() == null || dto.getDescripcion().isEmpty() ||
                dto.getCamas() == 0 || dto.getCapacidadHuespedes() == 0 ||
                dto.getBanios() == 0 || dto.getPiezas() == 0) {
            System.out.println("Validación fallida: " + dto);
            return false;
        }

        String idAnfitrionStr = obtenerInformacionCuentaService.obtenerId(dto.getCorreo());
        if (idAnfitrionStr == null) {
            System.out.println("No se encontró cuenta para correo: " + dto.getCorreo());
            return false;
        }
        System.out.println(idAnfitrionStr);

        Long idAnfitrion = Long.valueOf(idAnfitrionStr);
        Optional<Anfitrion> anfitrionOptional = anfitrionRepository.findById(idAnfitrion);
        if (anfitrionOptional.isEmpty()) {
            System.out.println("No se encontró anfitrión con id: " + idAnfitrion);
            return false;
        }
        Anfitrion anfitrion = anfitrionOptional.get();
        Anuncio anuncio = new Anuncio();
        anuncio.setAnfitrion(anfitrion);
        anuncio.setAnuTitulo(dto.getTitulo());
        anuncio.setAnuPrecioPorNoche(dto.getPrecioPorNoche());
        anuncio.setAnuDescripcion(dto.getDescripcion());
        anuncio.setAnuCamas(dto.getCamas());
        anuncio.setAnuCapacidadHuespedes(dto.getCapacidadHuespedes());
        anuncio.setAnuBanios(dto.getBanios());
        anuncio.setAnuPiezas(dto.getPiezas());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String fechaInicio = LocalDateTime.now().format(formatter);
        String fechaTermino = LocalDateTime.now().plusMonths(6).format(formatter);

        anuncio.setAnuFechaInicio(fechaInicio);
        anuncio.setAnuFechaTermino(fechaTermino);

        anuncioRepository.save(anuncio);
        return true;
    }

}



