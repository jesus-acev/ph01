package cl.ufro.dci.perfecthostapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Lanzador de la aplicación de Spring Boot del proyecto PerfectHost.
 *
 * @author oscar
 */
@SpringBootApplication
public class PerfecthostApiApplication {

    /**
     * Método que inicia la aplicación.
     *
     * @param args Argumentos ingresados por línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(PerfecthostApiApplication.class, args);
    }
}
