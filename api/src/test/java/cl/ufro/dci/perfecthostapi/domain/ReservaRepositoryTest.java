package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.ReservaRepository;
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
@DisplayName("Almacenamiento de reservas")
class ReservaRepositoryTest {

    @Autowired
    private ReservaRepository reservaRepository;

    private final File DATA_RES = Paths.get("src", "test", "resources", "reservas.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        Reserva[] reservas = new ObjectMapper().readValue(DATA_RES, Reserva[].class);

        // Save each entity to database
        Arrays.stream(reservas).forEach(reservaRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        reservaRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar reserva")
    void testSave() {
        // Prepare mock
        Reserva nuevo = new Reserva();
        nuevo.setResPrecioTotal(150000);
        nuevo.setResCantidadHuespedes(4);
        nuevo.setResFechaCreacion("2021-11-05");
        nuevo.setResFechaInicio("2021-11-20");
        nuevo.setResFechaTermino("2021-12-10");

        // Save
        Reserva guardado = reservaRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getResId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getResPrecioTotal(), guardado.getResPrecioTotal());
        Assertions.assertEquals(nuevo.getResCantidadHuespedes(), guardado.getResCantidadHuespedes());
        Assertions.assertEquals(nuevo.getResFechaCreacion(), guardado.getResFechaCreacion());
        Assertions.assertEquals(nuevo.getResFechaInicio(), guardado.getResFechaInicio());
        Assertions.assertEquals(nuevo.getResFechaTermino(), guardado.getResFechaTermino());
    }

    @Test
    @DisplayName("Obtener lista de reservas")
    void testGet() {
        List<Reserva> target = new ArrayList<>();
        reservaRepository.findAll().forEach(target::add);
        Assertions.assertEquals(2, target.size());
    }
}
