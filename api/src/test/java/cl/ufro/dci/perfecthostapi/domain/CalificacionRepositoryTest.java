package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.CalificacionRepository;
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
@DisplayName("Almacenamiento de calificaciones")
class CalificacionRepositoryTest {

    @Autowired
    private CalificacionRepository calificacionRepository;

    private final File DATA_CAL = Paths.get("src", "test", "resources", "calificaciones.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        Calificacion[] calificaciones = new ObjectMapper().readValue(DATA_CAL, Calificacion[].class);

        // Save each entity to database
        Arrays.stream(calificaciones).forEach(calificacionRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        calificacionRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar calificación")
    void testSave() {
        // Prepare mock
        Calificacion nuevo = new Calificacion();
        nuevo.setCalTitulo("Excelente");
        nuevo.setCalComentario("Excelente servicio");
        nuevo.setCalPuntaje(5);
        nuevo.setCalFechaCreacion("2019-20-05");

        // Save
        Calificacion guardado = calificacionRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getCalId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getCalTitulo(), guardado.getCalTitulo());
        Assertions.assertEquals(nuevo.getCalComentario(), guardado.getCalComentario());
        Assertions.assertEquals(nuevo.getCalPuntaje(), guardado.getCalPuntaje());
        Assertions.assertEquals(nuevo.getCalFechaCreacion(), guardado.getCalFechaCreacion());
    }

    @Test
    @DisplayName("Obtener lista de calificaciones")
    void testGet() {
        List<Calificacion> target = new ArrayList<>();
        calificacionRepository.findAll().forEach(target::add);
        Assertions.assertEquals(1, target.size());
    }
}
