package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.TipoCalificacionRepository;
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
@DisplayName("Almacenamiento de tipos de calificación (enum)")
class TipoCalificacionRepositoryTest {

    @Autowired
    private TipoCalificacionRepository tipoCalificacionRepository;

    // Nota: los datos del JSON son los mismos que se inicializan en el archivo data.sql
    private final File DATA_TCA = Paths.get("src", "test", "resources", "tipos_calificacion.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        TipoCalificacion[] tiposCalificaciones = new ObjectMapper().readValue(DATA_TCA, TipoCalificacion[].class);

        // Save each entity to database
        Arrays.stream(tiposCalificaciones).forEach(tipoCalificacionRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        tipoCalificacionRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar tipo de calificación")
    void testSave() {
        // Prepare mock
        TipoCalificacion nuevo = new TipoCalificacion();
        nuevo.setTcaNombre("Alojamiento");

        // Save
        TipoCalificacion guardado = tipoCalificacionRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getTcaId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getTcaNombre(), guardado.getTcaNombre());
    }

    @Test
    @DisplayName("Obtener lista de tipos de calificación")
    void testGet() {
        List<TipoCalificacion> target = new ArrayList<>();
        tipoCalificacionRepository.findAll().forEach(target::add);
        Assertions.assertEquals(1, target.size());
    }

    @Test
    @DisplayName("Obtener tipo de calificación según su nombre")
    void testFindByNombre() {
        TipoCalificacion target = tipoCalificacionRepository.findByTcaNombre("ALOJAMIENTO");

        Assertions.assertEquals(1, target.getTcaId());
        Assertions.assertEquals("ALOJAMIENTO", target.getTcaNombre());
    }
}
