package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.TipoComisionRepository;
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
@DisplayName("Almacenamiento de tipos de comisión (enum)")
class TipoComisionRepositoryTest {

    @Autowired
    private TipoComisionRepository tipoComisionRepository;

    // Nota: los datos del JSON son los mismos que se inicializan en el archivo data.sql
    private final File DATA_TCO = Paths.get("src", "test", "resources", "tipos_comision.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        TipoComision[] tiposComisiones = new ObjectMapper().readValue(DATA_TCO, TipoComision[].class);

        // Save each entity to database
        Arrays.stream(tiposComisiones).forEach(tipoComisionRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        tipoComisionRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar tipo de comisión")
    void testSave() {
        // Prepare mock
        TipoComision nuevo = new TipoComision();
        nuevo.setTcoNombre("Reembolso");

        // Save
        TipoComision guardado = tipoComisionRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getTcoId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getTcoNombre(), guardado.getTcoNombre());
    }

    @Test
    @DisplayName("Obtener lista de tipos de comisión")
    void testGet() {
        List<TipoComision> target = new ArrayList<>();
        tipoComisionRepository.findAll().forEach(target::add);
        Assertions.assertEquals(5, target.size());
    }

    @Test
    @DisplayName("Obtener tipo de comisión según su nombre")
    void testFindByNombre() {
        TipoComision target = tipoComisionRepository.findByTcoNombre("RESERVA");

        Assertions.assertEquals(3, target.getTcoId());
        Assertions.assertEquals("RESERVA", target.getTcoNombre());
    }
}
