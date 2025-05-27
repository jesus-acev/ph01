package cl.ufro.dci.perfecthostapi.service;

import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import cl.ufro.dci.perfecthostapi.dto.AnuncioDTO;
import cl.ufro.dci.perfecthostapi.repository.AnuncioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Lógica para la busqueda de anuncio por coordenadas, fechas de alojamiento y número de huespedes (+dar 20 lugares aleatorios).
 * Clase poco escalable, se suguiere que en el futuro las busquedas sean por criterios de texto (pais,ciudad,continente),
 * y las listas de cada lugar esten pre-hechas en la base de datos, para ahorar tiempo de cálculo.
 *
 * @author Cristóbal Contreras
 * @version 1.0
 */
@Service
public class BuscarAnunciosService {

    @Autowired
    AnuncioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Método que entrega todos los aunucios existentes al moomento de ejecución
     *
     * @return Lista de Anuncios existentes
     */
    public List<AnuncioDTO> listarAnuncios() {
        List<Anuncio> anuncios = new ArrayList<>();
        repository.findAll().forEach(anuncios::add);
        // Mapear a DTO
        return anuncios.stream()
                .map(anuncio -> modelMapper.map(anuncio, AnuncioDTO.class))
                .collect(Collectors.toList());
    }

    public List<AnuncioDTO> anunciosPorCorreo(String correo) {
        return repository.findAllByAnfitrionCueCorreo(correo).stream()
                .map(anuncio -> modelMapper.map(anuncio, AnuncioDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método que entrega 20 alojamientos aleatorios.
     *
     * @return Lista de Anuncios aleatorios
     */
    public List<Anuncio> aleatorios20() {
        List<Anuncio> anuncios = new ArrayList<>();
        repository.findAll().forEach(anuncios::add);
        var rng = new Random(32893360);
        var rNumber = 0;
        List<Anuncio> seleccion = new ArrayList<>();
        for (var i = 0; i < 20; i++) {
            rNumber = rng.nextInt(anuncios.size() - 1);
            //Se remueven para evitar posible duplicidad
            seleccion.add(anuncios.remove(rNumber));
        }
        return seleccion;
    }

    /**
     * Metodo que entrega todos los anuncios que se encuentran a menos de 54Km (+ o -) del centro de la ciudad buscada.
     *
     * @param coorBusq coordenadas con la forma (latitud,longitud)
     * @return Lista de Anuncios cercanos
     */
    public List<Anuncio> buscarXCoordenadas(String coorBusq) {
        List<Anuncio> anuncios = new ArrayList<>();
        repository.findAll().forEach(anuncios::add);
        return seleccionarXCercania(anuncios, coorBusq);
    }

    /**
     * Método que entrega los anuncios que se encuantran en los rango de fecha que el huesped solicitó
     *
     * @param fechaInicio  String con formato YYYY-MM-dd, que describe la fecha que iniciara el hospedaje
     * @param fechaTermino String con formato YYYY-MM-dd, que describe la fecha que terminara el hospedaje.
     * @return Lista con los anuncios dentro del rango de fechas.
     */
    public List<Anuncio> buscarXRangoFechas(String fechaInicio,
                                            String fechaTermino) {
        List<Anuncio> anuncios = new ArrayList<>();
        repository.findAll().forEach(anuncios::add);
        return seleccionarxFechas(anuncios, fechaInicio, fechaTermino);
    }

    /**
     * Método que entrega los anuncios que tienen una capacidad mayor a la solicitada por el huesped.
     *
     * @param huespedes Capacidad (mayor que 0) entregada por el huesped.
     * @return Lista de anuncios que cumplen con la capacidad solicitada.
     */
    public List<Anuncio> buscarxNoHuespedes(int huespedes) {
        List<Anuncio> anuncios = new ArrayList<>();
        repository.findAll().forEach(anuncios::add);
        return seleccionarxHuespedes(anuncios, huespedes);
    }

    /**
     * Método que aplica todos los criterios de busqueda para
     *
     * @param coorBusq     coordenadas con la forma (latitud,longitud)
     * @param fechaInicio  String con formato YYYY-MM-dd, que describe la fecha que iniciara el hospedaje
     * @param fechaTermino String con formato YYYY-MM-dd, que describe la fecha que terminara el hospedaje.
     * @param huespedes    Capacidad (mayor que 0) entregada por el huesped.
     * @return Lista con los anuncios que cumplen con el criterio
     */
    public List<Anuncio> buscarxTodo(String coorBusq,
                                     String fechaInicio,
                                     String fechaTermino,
                                     int huespedes) {
        List<Anuncio> anuncios = new ArrayList<>();
        repository.findAll().forEach(anuncios::add);
        return seleccionarXCercania(
                seleccionarxFechas(
                        seleccionarxHuespedes(anuncios,
                                huespedes),
                        fechaInicio,
                        fechaTermino),
                coorBusq);
    }
    /**
     * Método complementario que hace la seleccion de anuncios por criterio cantidad de huespedes.
     *
     * @param anuncios  Lista de anuncios a seleccionar
     * @param huespedes Entero con la capacidad de huespedes solicitada.
     * @return Lista de anuncios que cumplen con esa capacidad minima.
     */
    private List<Anuncio> seleccionarxHuespedes(List<Anuncio> anuncios,
                                                int huespedes) {
        Predicate<Anuncio> predicate = (Anuncio anun) -> (anun.getAnuCapacidadHuespedes() < huespedes);
        anuncios.removeIf(predicate);
        return anuncios;
    }
    /**
     * Método complementario que hace la seleccion de anuncios por criterio de fechas de inicio de alojamiento y termino
     * de este.
     *
     * @param anuncios     Lista de anuncios a seleccionar
     * @param fechaInicio  String con formato YYYY-MM-dd, que describe la fecha que iniciara el hospedaje
     * @param fechaTermino String con formato YYYY-MM-dd, que describe la fecha que terminara el hospedaje.
     * @return Lista con los anuncios cuyas fechas disponibles estan entre las fechas solicitadas
     */
    private List<Anuncio> seleccionarxFechas(List<Anuncio> anuncios,
                                             String fechaInicio,
                                             String fechaTermino) {
        //Patron supuesto para fechas YYYY-MM-dd, corregir si no es el final.
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

        var fechaIni = LocalDate.parse(fechaInicio, dtf);
        var fechaFin = LocalDate.parse(fechaTermino, dtf);

        for (Anuncio anuncio : anuncios) {

            var fechaIniAnun = LocalDate.parse(anuncio.getAnuFechaInicio(), dtf);
            var fechaFinAnun = LocalDate.parse(anuncio.getAnuFechaTermino(), dtf);

            if (fechaIni.isBefore(fechaIniAnun) || fechaFin.isAfter(fechaFinAnun)) {
                anuncios.remove(anuncio);
            }
        }
        return anuncios;
    }

    /**
     * Método complementario que hace la seleccion de anuncios por criterio de cercania a la coordenada de la ciudad
     * solicitada
     *
     * @param anuncios Lista de anuncios a seleccionar
     * @param coorBusq coordenadas con la forma (latitud,longitud)
     * @return Lista de anuncios que se encuentran cercanos a la ciudad solicitada.
     */
    private List<Anuncio> seleccionarXCercania(List<Anuncio> anuncios,
                                               String coorBusq) {

        String[] coorBusqArray = separarCoor(coorBusq);
        var latBusq = Double.parseDouble(coorBusqArray[0]);
        var lonBusq = Double.parseDouble(coorBusqArray[1]);

        TreeMap<Double, Anuncio> anuncioCercanos = new TreeMap<>();

        for (Anuncio anuncio : anuncios) {
            String[] coorAnun = separarCoor(anuncio.getAnuCoordenadas());
            var latAnun = Double.parseDouble(coorAnun[0]);
            var lonAnun = Double.parseDouble(coorAnun[1]);

            double dist = Math.sqrt(Math.pow(latAnun - latBusq, 2) + Math.pow(lonAnun - lonBusq, 2));

            if (dist < ((double) 30 / (double) 60)) {
                anuncioCercanos.put(dist, anuncio);
            }
        }
        return new ArrayList<>(anuncioCercanos.values());
    }

    /**
     * Método complementario que toma el formato de las coordenadas dadas y lo transforma en un array con primer elemento
     * la latitud y segundo la longitud
     * @param coor Coordenadas con la forma (latitud,longitud)
     * @return Array de dos elementos con latitud en [0] y longitud en [1]
     */
    private String[] separarCoor(String coor){
        return coor.replace("(","").replace(")","").split(",");
    }
}
