package cl.ufro.dci.perfecthostapi.controller.anuncios;

import cl.ufro.dci.perfecthostapi.domain.Anuncio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>GeneratePdfController class.</p>
 *
 * @author pablo
 */
public class GeneratePdfController {

    private final Document document;

    /**
     * <p>Constructor for GeneratePdfController.</p>
     */
    public GeneratePdfController() {
        this.document = new Document();
    }

    /**
     * Método que se encarga de generar un archivo .pdf con la información de un anuncio.
     *
     * @param anuncio objeto que corresponde anuncio del cual se le extrae la información.
     */
    public void generate(Anuncio anuncio) {
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Formulario_Anuncio.pdf"));
            document.open();
            var font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
            var titulo = new Chunk("PerfectHost Formulario\n\n\n", font);
            document.add(titulo);
        } catch (IOException | DocumentException e) {
            System.out.println("Ha ocurrido un error");
        }
        informacionFormularioAnuncio(anuncio);
        document.close();
    }

    /**
     * Método que ingresa los datos importantes de un anuncio en el el archivo .pdf
     *
     * @param anuncio a {@link cl.ufro.dci.perfecthostapi.domain.Anuncio} object.
     */
    public void informacionFormularioAnuncio(Anuncio anuncio) {
        var tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);

        tabla.addCell(new Phrase("" + anuncio.getAnuId()));
        tabla.addCell(new Phrase("" + anuncio.getAnuTitulo()));
        tabla.addCell(new Phrase("" + anuncio.getAnuPrecioPorNoche()));
        tabla.addCell(new Phrase("" + anuncio.getAnuDescripcion()));
        try {
            this.document.add(tabla);
        } catch (DocumentException e) {
            System.out.println("Ha ocurrido un error");
        }
    }
}
