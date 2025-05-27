package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.EstadoReservaRepository;
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
@DisplayName("Almacenamiento de estados de reserva (enum)")
class EstadoReservaRepositoryTest {

    @Autowired
    private EstadoReservaRepository estadoReservaRepository;

    // Nota: los datos del JSON son los mismos que se inicializan en el archivo data.sql
    private final File DATA_ERS = Paths.get("src", "test", "resources", "estados_reserva.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        EstadoReserva[] estadosReserva = new ObjectMapper().readValue(DATA_ERS, EstadoReserva[].class);

        // Save each entity to database
        Arrays.stream(estadosReserva).forEach(estadoReservaRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        estadoReservaRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar estado de reserva")
    void testSave() {
        // Prepare mock
        EstadoReserva nuevo = new EstadoReserva();
        nuevo.setErsNombre("Reservada");

        // Save
        EstadoReserva guardado = estadoReservaRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getErsId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getErsNombre(), guardado.getErsNombre());
    }

    @Test
    @DisplayName("Obtener lista de estados de reserva")
    void testGet() {
        List<EstadoReserva> target = new ArrayList<>();
        estadoReservaRepository.findAll().forEach(target::add);
        Assertions.assertEquals(4, target.size());
    }

    @Test
    @DisplayName("Obtener estado de reserva según su nombre")
    void testFindByNombre() {
        EstadoReserva target = estadoReservaRepository.findByErsNombre("FINALIZADA");

        Assertions.assertEquals(4, target.getErsId());
        Assertions.assertEquals("FINALIZADA", target.getErsNombre());
    }
}
