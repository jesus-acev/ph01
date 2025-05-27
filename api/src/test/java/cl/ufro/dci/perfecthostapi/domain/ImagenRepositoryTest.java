package cl.ufro.dci.perfecthostapi.domain;

import cl.ufro.dci.perfecthostapi.repository.ImagenRepository;
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
@DisplayName("Almacenamiento de imágenes")
class ImagenRepositoryTest {

    @Autowired
    private ImagenRepository imagenRepository;

    private final File DATA_IMG = Paths.get("src", "test", "resources", "imagenes.json").toFile();

    @BeforeEach
    public void setUp() throws IOException {
        // Deserialize entity from JSON file to entity array
        Imagen[] imagenes = new ObjectMapper().readValue(DATA_IMG, Imagen[].class);

        // Save each entity to database
        Arrays.stream(imagenes).forEach(imagenRepository::save);
    }

    @AfterEach
    public void cleanup() {
        // Cleanup the database after each test
        imagenRepository.deleteAll();
    }

    @Test
    @DisplayName("Guardar imagen")
    void testSave() {
        // Prepare mock
        Imagen nuevo = new Imagen();
        nuevo.setImgImagen(new byte[]{1, 2, 3});

        // Save
        Imagen guardado = imagenRepository.save(nuevo);

        // Assert
        Assertions.assertNotNull(guardado, "No debería ser nulo");
        Assertions.assertNotNull(guardado.getImgId(), "Debería tener un id al guardarse");
        Assertions.assertEquals(nuevo.getImgImagen(), guardado.getImgImagen());
    }

    @Test
    @DisplayName("Obtener lista de imágenes")
    void testGet() {
        List<Imagen> target = new ArrayList<>();
        imagenRepository.findAll().forEach(target::add);
        Assertions.assertEquals(2, target.size());
    }
}
