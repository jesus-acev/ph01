package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.HuespedRepository;
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
@DisplayName("Almacenamiento de huéspedes")
class HuespedRepositoryTest {

    @Autowired
    private HuespedRepository huespedRepository;

    private final File DATA_HUE = Paths.get("src", "test", "resources", "huespedes.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        Huesped[] huespedes = new ObjectMapper().readValue(DATA_HUE, Huesped[].class);

        // Save each entity to database
        Arrays.stream(huespedes).forEach(huespedRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        huespedRepository.deleteAll();
    }


    @Test
    @DisplayName("Guardar huésped")
    void testSave() {
        // Prepare mock
        Huesped nuevo = new Huesped();
        nuevo.setCueNombreUsuario("Homero Simpson");
        nuevo.setCueClave("clavehuesped");
        nuevo.setCueFotoPerfil(new byte[]{10, 20, 30, 40});
        nuevo.setCueTelefono("90909090");
        nuevo.setCueCorreo("hsimpson@organizacion.com");
        nuevo.setCueIdBanco("4455667788");
        nuevo.setCueSaldo(50000);
        nuevo.setCueFechaCreacion("2018-03-03");

        // Save
        Huesped guardado = huespedRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getCueId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getCueNombreUsuario(), guardado.getCueNombreUsuario());
        Assertions.assertEquals(nuevo.getCueClave(), guardado.getCueClave());
        Assertions.assertEquals(nuevo.getCueFotoPerfil(), guardado.getCueFotoPerfil());
        Assertions.assertEquals(nuevo.getCueTelefono(), guardado.getCueTelefono());
        Assertions.assertEquals(nuevo.getCueCorreo(), guardado.getCueCorreo());
        Assertions.assertEquals(nuevo.getCueIdBanco(), guardado.getCueIdBanco());
        Assertions.assertEquals(nuevo.getCueSaldo(), guardado.getCueSaldo());
        Assertions.assertEquals(nuevo.getCueFechaCreacion(), guardado.getCueFechaCreacion());
    }

    @Test
    @DisplayName("Obtener lista de huéspedes")
    void testGet() {
        List<Huesped> target = new ArrayList<>();
        huespedRepository.findAll().forEach(target::add);
        Assertions.assertEquals(1, target.size());
    }

    @Test
    @DisplayName("Obtener cuenta (huésped) según su nombre")
    void testFindByNombre() {
        Huesped target = (Huesped) huespedRepository.findByCueNombreUsuario("Juan");

        Assertions.assertNotNull(target.getCueId(), "Debería tener un id no nulo");
        Assertions.assertEquals("juan@mail.com", target.getCueCorreo());
    }

    @Test
    @DisplayName("Obtener cuenta (huésped) según su correo")
    void testFindByCorreo() {
        Huesped target = (Huesped) huespedRepository.findByCueCorreo("juan@mail.com");

        Assertions.assertNotNull(target.getCueId(), "Debería tener un id no nulo");
        Assertions.assertEquals("juan@mail.com", target.getCueCorreo());
    }
}
