package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.AnuncioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith({SpringExtension.class})
@SpringBootTest
@DisplayName("Almacenamiento de anuncios")
class AnuncioRepositoryTest {

    @Autowired
    private AnuncioRepository anuncioRepository;

    private final File DATA_ANU = Paths.get("src", "test", "resources", "anuncios.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        Anuncio[] anuncios = new ObjectMapper().readValue(DATA_ANU, Anuncio[].class);

        // Save each entity to database
        Arrays.stream(anuncios).forEach(anuncioRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        anuncioRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar anuncio")
    void testSave() {
        // Prepare mock
        Anuncio nuevo = new Anuncio();
        nuevo.setAnuTitulo("Casa buena");
        nuevo.setAnuPrecioPorNoche(25000);
        nuevo.setAnuCalificacionTotal(5);
        nuevo.setAnuDescripcion("Una casa muy buena");
        nuevo.setAnuCamas(1);
        nuevo.setAnuCapacidadHuespedes(1);
        nuevo.setAnuBanios(1);
        nuevo.setAnuPiezas(1);
        nuevo.setAnuFechaCreacion("2020-04-30");
        nuevo.setAnuFechaInicio("2020-05-01");
        nuevo.setAnuFechaTermino("2020-12-20");
        nuevo.setAnuCoordenadas("(30, 30)");
        nuevo.setAnuReglas("Cuidar al perro");
        nuevo.setAnuServicios("Estacionamiento gratuito");

        // Save
        Anuncio guardado = anuncioRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getAnuId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getAnuTitulo(), guardado.getAnuTitulo());
        Assertions.assertEquals(nuevo.getAnuPrecioPorNoche(), guardado.getAnuPrecioPorNoche());
        Assertions.assertEquals(nuevo.getAnuCalificacionTotal(), guardado.getAnuCalificacionTotal());
        Assertions.assertEquals(nuevo.getAnuDescripcion(), guardado.getAnuDescripcion());
        Assertions.assertEquals(nuevo.getAnuCamas(), guardado.getAnuCamas());
        Assertions.assertEquals(nuevo.getAnuCapacidadHuespedes(), guardado.getAnuCapacidadHuespedes());
        Assertions.assertEquals(nuevo.getAnuBanios(), guardado.getAnuBanios());
        Assertions.assertEquals(nuevo.getAnuPiezas(), guardado.getAnuPiezas());
        Assertions.assertEquals(nuevo.getAnuFechaCreacion(), guardado.getAnuFechaCreacion());
        Assertions.assertEquals(nuevo.getAnuFechaInicio(), guardado.getAnuFechaInicio());
        Assertions.assertEquals(nuevo.getAnuFechaTermino(), guardado.getAnuFechaTermino());
        Assertions.assertEquals(nuevo.getAnuCoordenadas(), guardado.getAnuCoordenadas());
        Assertions.assertEquals(nuevo.getAnuReglas(), guardado.getAnuReglas());
        Assertions.assertEquals(nuevo.getAnuServicios(), guardado.getAnuServicios());
    }

    @Test
    @DisplayName("Obtener lista de anuncios")
    void testGet() {
        List<Anuncio> target = new ArrayList<>();
        anuncioRepository.findAll().forEach(target::add);
        Assertions.assertEquals(1, target.size());
    }
}
