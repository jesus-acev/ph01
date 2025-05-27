package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.AnfitrionRepository;
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
@DisplayName("Almacenamiento de anfitriones")
class AnfitrionRepositoryTest {

    @Autowired
    private AnfitrionRepository anfitrionRepository;

    private final File DATA_ANF = Paths.get("src", "test", "resources", "anfitriones.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        Anfitrion[] anfitriones = new ObjectMapper().readValue(DATA_ANF, Anfitrion[].class);

        // Save each entity to database
        Arrays.stream(anfitriones).forEach(anfitrionRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        anfitrionRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar anfitrión")
    void testSave() {
        // Prepare mock
        Anfitrion nuevo = new Anfitrion();
        nuevo.setCueNombreUsuario("Juan Pérez");
        nuevo.setCueClave("qwertyuiop");
        nuevo.setCueFotoPerfil(new byte[]{1, 1, 2, 2, 3, 3, 4, 4});
        nuevo.setCueTelefono("112233445");
        nuevo.setCueCorreo("jperez@organizacion.com");
        nuevo.setCueIdBanco("73829416328");
        nuevo.setCueSaldo(100000);
        nuevo.setCueFechaCreacion("2019-05-05");
        nuevo.setAnfCalificacionMedia(3);
        nuevo.setAnfEmpresa("Organización");

        // Save
        Anfitrion guardado = anfitrionRepository.save(nuevo);

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
        Assertions.assertEquals(nuevo.getAnfCalificacionMedia(), guardado.getAnfCalificacionMedia());
        Assertions.assertEquals(nuevo.getAnfEmpresa(), guardado.getAnfEmpresa());
    }

    @Test
    @DisplayName("Obtener lista de anfitriones")
    void testGet() {
        List<Anfitrion> target = new ArrayList<>();
        anfitrionRepository.findAll().forEach(target::add);
        Assertions.assertEquals(1, target.size());
    }

    @Test
    @DisplayName("Obtener cuenta (anfitrión) según su nombre")
    void testFindByNombre() {
        Anfitrion target = (Anfitrion) anfitrionRepository.findByCueNombreUsuario("John");

        Assertions.assertNotNull(target.getCueId(), "Debería tener un id no nulo");
        Assertions.assertEquals("John", target.getCueNombreUsuario());
    }

    @Test
    @DisplayName("Obtener cuenta (anfitrión) según su correo")
    void testFindByCorreo() {
        Anfitrion target = (Anfitrion) anfitrionRepository.findByCueCorreo("john@mail.com");

        Assertions.assertNotNull(target.getCueId(), "Debería tener un id no nulo");
        Assertions.assertEquals("john@mail.com", target.getCueCorreo());
    }
}
