package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.ComisionRepository;
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
@DisplayName("Almacenamiento de comisiones")
class ComisionRepositoryTest {

    @Autowired
    private ComisionRepository comisionRepository;

    private final File DATA_COM = Paths.get("src", "test", "resources", "comisiones.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        Comision[] comisiones = new ObjectMapper().readValue(DATA_COM, Comision[].class);

        // Save each entity to database
        Arrays.stream(comisiones).forEach(comisionRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        comisionRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar comisión")
    void testSave() {
        // TODO
        // Prepare mock
        Comision nuevo = new Comision();
        nuevo.setComIdTransaccion("trs_12345");
        nuevo.setComValor(50000L);
        nuevo.setComFechaCreacion("2020-07-07");

        // Save
        Comision guardado = comisionRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getComId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getComIdTransaccion(), guardado.getComIdTransaccion());
        Assertions.assertEquals(nuevo.getComValor(), guardado.getComValor());
        Assertions.assertEquals(nuevo.getComFechaCreacion(), guardado.getComFechaCreacion());
    }

    @Test
    @DisplayName("Obtener lista de comisiones")
    void testGet() {
        List<Comision> target = new ArrayList<>();
        comisionRepository.findAll().forEach(target::add);
        Assertions.assertEquals(4, target.size());
    }
}
