package cl.ufro.dci.perfecthostapi.controller.site;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>SiteController class.</p>
 *
 * @author oscar
 */
@RestController
@RequestMapping("/")
class SiteController {

    @GetMapping
    public String index() {
        return "<h1>Bienvenido a la API de PerfectHost</h1>";
    }


}
