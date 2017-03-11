/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporteFertilizacion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import entities.fertilizacion.Cliente;
import entities.fertilizacion.Cultivo;
import entities.fertilizacion.Hacienda;
import entities.fertilizacion.Lote;
import entities.fertilizacion.MuestraLaboratorio;
import entities.fertilizacion.ResultadoLaboratorio;
import entities.fertilizacion.Variedad;
import entities.reportes.TextoReporteResultadoLaboratorio;
import helpers.Canton;
import java.io.IOException;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;

/**
 *
 * @author Luduan
 */
@WebServlet(name = "ReporteResultadoLaboratorio", urlPatterns = {"/ReporteResultadoLaboratorio"})
public class ReporteResultadoLaboratorio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        try {
            Font font9BoldWhite = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, BaseColor.WHITE);
            Font font7Bold = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
            Font font7Normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
            Font font8Bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
            Font font8Normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);
            Font font9Bold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
            Font font9Normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
            Font font10Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
            Font font10Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
            Font font12Bold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
            Font font12Normal = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
            Font font14Bold = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
            Font font14Normal = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL);
            Font font18Bold = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
            Font font22Bold = FontFactory.getFont(FontFactory.HELVETICA, 22, Font.BOLD);

            //obtencion parametros *********************************************************
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Map params = facesContext.getExternalContext().getRequestParameterMap();
            ObjectId parametroObtenido = new ObjectId(params.get("idMuestra").toString());

            ResultadoLaboratorio resLab = ResultadoLaboratorio.getResultadoLaboratorioById(parametroObtenido);
            MuestraLaboratorio muestra = MuestraLaboratorio.getMuestraLaboratorioById(resLab.getMuestra());
            Cliente cli = Cliente.getClienteById(muestra.getCliente());
            Hacienda hac = Hacienda.getHaciendaById(muestra.getHacienda());
            //Lote lot = Lote.getLoteById(muestra.getLote());
            String lot = muestra.getLote();
            Cultivo cul = Cultivo.getCultivoById(muestra.getCultivo());

            String cliente = cli.getNombre().toUpperCase();
            String ubicacion = Canton.getCantonById(cli.getCanton()).getNombre() + " - " + Canton.getCantonById(cli.getCanton()).getLeyendaProvincia() + " - " + Canton.getCantonById(cli.getCanton()).getLeyendaPais();
            String codigMuestra = muestra.getCodigo();

            String hacienda = hac.getNombre();
            String cultivo = cul.getNombre();            
            //String varie = Variedad.getVariedadById(lot.getIdVariedad()).getNombre();
            String varie = muestra.getLoteCompleto().getLeyendaVariedad();
            //String estacionMonitoreo = lot.getEstacion().get(0).getCodigo();
            String estacionMonitoreo = muestra.getLoteCompleto().getListaEstacionMonitoreo().get(0).getCodigo();
            //String estacionMonitoreo = muestra.getLoteCompleto().getListadoMonitoreo().get(0).getCodigo();
            String tituloProyecto = "Análisis" + resLab.getLeyendaMatriz();
            String tipoMuestra = resLab.getLeyendaMatriz();
            String numeroMuestra = Integer.toString(MuestraLaboratorio.getNumberMuestraLaboratorio(muestra.getId(), cli.getId(), hac.getId(), muestra.getLote(), cul.getId()));
            String numeroMonitoreo = numeroMuestra;
            //String lote = lot.getCodigo();
            String lote = muestra.getLoteCompleto().getIdLotes();
            String muestreador = muestra.getMuestreador();
            String fechaMuestreo = muestra.getFechaFormatMuestreo();
            String fechaRecepcionMuestra = muestra.getFechaFormatEnvio();
            String fechaResultado = resLab.getFechaFormatResultado();
            String periodoPrueba = resLab.getFechaFormatRecepcion() + " al " + resLab.getFechaFormatResultado();

            String contenidoHoja1 = TextoReporteResultadoLaboratorio.getByReferencia("HOJA 1 TEXTO INFORME").getDescripcion();
            String responsableTecnico = TextoReporteResultadoLaboratorio.getByReferencia("RESPONSABLE TECNICO").getDescripcion();
            String datosLaboratorio = TextoReporteResultadoLaboratorio.getByReferencia("DATOS LABORATORIO").getDescripcion();
            String piePagina = TextoReporteResultadoLaboratorio.getByReferencia("PIE DE PAGINA").getDescripcion();
            String contenidoHoja2 = TextoReporteResultadoLaboratorio.getByReferencia("HOJA 2 OBSERVACION").getDescripcion();

            //fin obtencion parametros *****************************************************

            /* TODO output your page here. You may use following sample code. */
            float left = 60;
            float right = 30;
            float top = 0;
            float bottom = 0;
            Document document = new Document(PageSize.A4, left, right, top, bottom);
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            //FOOTER TABLE *****************************************************************
            PdfPTable table = new PdfPTable(1);
            table.setTotalWidth(550);
            PdfPCell cell = new PdfPCell(new Phrase(piePagina, font8Normal));
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("www.agrorum.com.ec", font9BoldWhite));
            cell.setBackgroundColor(BaseColor.ORANGE);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            table.addCell(cell);

            FooterTable event = new FooterTable(table);
            writer.setPageEvent(event);
            //FIN FOOTER TABLE *****************************************************************

            document.open();

            //HOJA 1 *********************************************************************************************************************************                        
            //IMAGE **********************************************************************************
            String relativeWebPathAg = "/images/agrorum.png";
            String absoluteDiskPathAg = getServletContext().getRealPath(relativeWebPathAg);
            Image logoAg = Image.getInstance(absoluteDiskPathAg);
            logoAg.setAbsolutePosition(10f, 750f);
            logoAg.scalePercent(80f);
            document.add(logoAg);

            String relativeWebPathEu = "/images/eurofins.png";
            String absoluteDiskPathEu = getServletContext().getRealPath(relativeWebPathEu);
            Image logoEu = Image.getInstance(absoluteDiskPathEu);
            logoEu.setAbsolutePosition(500f, 770f);
            logoEu.scalePercent(60f);
            document.add(logoEu);
            //FIN IMAGE ********************************************************************************         

            //CLIENTE UBICACION MUESTRA*****************************************************************
            Paragraph parphRepor = new Paragraph("Reporte de análisis 1/2", font10Normal);
            parphRepor.setAlignment(Element.ALIGN_RIGHT);
            parphRepor.setSpacingBefore(90f);
            Paragraph parphCli = new Paragraph(cliente, font10Bold);
            parphCli.setAlignment(Element.ALIGN_LEFT);
            parphCli.setSpacingBefore(15f);
            Paragraph parphUbi = new Paragraph(ubicacion, font10Bold);
            parphUbi.setAlignment(Element.ALIGN_LEFT);
            parphUbi.setSpacingBefore(15f);
            //PARRAFO combinado con frases********************************************
            Phrase p1 = new Phrase("Código de la Muestra:     ", font10Normal);
            Phrase p2 = new Phrase(codigMuestra, font10Bold);
            Paragraph parphMue = new Paragraph(p1);
            parphMue.add(p2);
            parphMue.setAlignment(Element.ALIGN_LEFT);
            parphMue.setSpacingBefore(15f);
            //FIN PARRAFO combinado***************************************************

            document.add(parphRepor);
            document.add(parphCli);
            document.add(parphUbi);
            document.add(parphMue);

            //FIN CLIENTE UBICACION MUESTRA*************************************************************
            //TABLA ***********************************************************************************
            PdfPTable tableDatos = new PdfPTable(new float[]{0.20f, 0.80f});
            tableDatos.setWidthPercentage(100);

            tableDatos.addCell(getCell("Propiedad:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(hacienda, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Cultivo Actual:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(cultivo, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Estación Monitoreo:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(estacionMonitoreo, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Título Proyecto:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(tituloProyecto, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Tipo Muestra:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(tipoMuestra, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Número Muestra:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(numeroMuestra, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Número Monitoreo:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(numeroMonitoreo, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Lote:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(lote, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Muestreador:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(muestreador.toUpperCase(), PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Fecha Muestreo:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(fechaMuestreo, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Recepción Muestra:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(fechaRecepcionMuestra, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.addCell(getCell("Período de Prueba:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatos.addCell(getCell(periodoPrueba, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatos.setSpacingBefore(20f);
            document.add(tableDatos);
            //FIN TABLA ********************************************************************************

            //CONTENIDO ********************************************************************************
            Paragraph parphCont = new Paragraph(contenidoHoja1, font8Normal);
            parphCont.setAlignment(Element.ALIGN_JUSTIFIED);
            parphCont.setSpacingBefore(20f);

            Paragraph parphLLeida = new Paragraph("LLeida, " + fechaResultado, font8Normal);
            parphLLeida.setAlignment(Element.ALIGN_LEFT);
            parphLLeida.setSpacingBefore(20f);

            Paragraph parphTecnico = new Paragraph(responsableTecnico, font9Bold);
            parphTecnico.setAlignment(Element.ALIGN_LEFT);
            parphTecnico.setSpacingBefore(20f);

            Paragraph parphResponsable = new Paragraph("Responsable Técnico", font8Normal);
            parphResponsable.setAlignment(Element.ALIGN_LEFT);
            parphResponsable.setSpacingBefore(1f);

            Paragraph parphLaboratorio = new Paragraph(datosLaboratorio, font8Bold);
            parphLaboratorio.setAlignment(Element.ALIGN_LEFT);
            parphLaboratorio.setSpacingBefore(30f);

            document.add(parphCont);
            document.add(parphLLeida);
            document.add(parphTecnico);
            document.add(parphResponsable);
            document.add(parphLaboratorio);
            //FIN CONTENIDO ****************************************************************************

            //FIN HOJA 1 *****************************************************************************************************************************
            document.newPage();

            //HOJA 2 *********************************************************************************************************************************                        
            //IMAGE **********************************************************************************
            String relativeWebPathAg1 = "/images/agrorum.png";
            String absoluteDiskPathAg1 = getServletContext().getRealPath(relativeWebPathAg1);
            Image logoAg1 = Image.getInstance(absoluteDiskPathAg1);
            logoAg1.setAbsolutePosition(10f, 750f);
            logoAg1.scalePercent(80f);
            document.add(logoAg1);

            String relativeWebPathEu1 = "/images/eurofins.png";
            String absoluteDiskPathEu1 = getServletContext().getRealPath(relativeWebPathEu1);
            Image logoEu1 = Image.getInstance(absoluteDiskPathEu1);
            logoEu1.setAbsolutePosition(500f, 770f);
            logoEu1.scalePercent(60f);
            document.add(logoEu1);

            Paragraph ini = new Paragraph("", font12Bold);
            ini.setAlignment(Element.ALIGN_CENTER);
            ini.setSpacingBefore(100f);

           

            PdfPTable cabT = new PdfPTable(new float[]{1f});
             cabT.setWidthPercentage(100);
            PdfPCell cellCab = new PdfPCell(new Phrase("Informe de Prueba", font12Bold));            
            cellCab.setPadding(2f);
            cellCab.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabT.addCell(cellCab);

            Paragraph parphRep = new Paragraph("Reporte de análisis 2/2", font10Normal);
            parphRep.setAlignment(Element.ALIGN_RIGHT);

            document.add(ini);
            document.add(cabT);
            document.add(parphRep);

            //TABLA ***********************************************************************************
            PdfPTable tableDatosH2 = new PdfPTable(new float[]{0.20f, 0.80f});
            tableDatosH2.setWidthPercentage(100);

            tableDatosH2.addCell(getCell("Cliente:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(cliente, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(" ", PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Propiedad:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(hacienda, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Estación Monitoreo:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(estacionMonitoreo, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Número Monitoreo:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(numeroMuestra, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Codigo Muestra:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(codigMuestra, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Matriz:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(tipoMuestra, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Cultivo:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(cultivo, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Variedad:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(varie, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Fecha Muestreo:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(fechaMuestreo, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Recepción Muestra:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(fechaRecepcionMuestra, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.addCell(getCell("Período de Prueba:", PdfPCell.ALIGN_LEFT, font8Normal));
            tableDatosH2.addCell(getCell(periodoPrueba, PdfPCell.ALIGN_LEFT, font8Bold));

            tableDatosH2.setSpacingBefore(20f);
            document.add(tableDatosH2);
            //FIN TABLA ********************************************************************************

            Paragraph parphCont1 = new Paragraph(contenidoHoja2, font8Normal);
            parphCont1.setAlignment(Element.ALIGN_JUSTIFIED);
            parphCont1.setSpacingBefore(20f);

            PdfPTable tableDatosH3 = new PdfPTable(new float[]{0.30f, 0.70f});
            tableDatosH3.setWidthPercentage(100);

            tableDatosH3.addCell(getCell(datosLaboratorio, PdfPCell.ALIGN_LEFT, font8Bold));

            PdfPTable subTable = new PdfPTable(new float[]{1f});
            subTable.setWidthPercentage(100);

            subTable.addCell(getCell("LLeida, " + fechaResultado, PdfPCell.ALIGN_CENTER, font8Normal));
            subTable.addCell(getCell(" ", PdfPCell.ALIGN_CENTER, font8Normal));
            subTable.addCell(getCell(" ", PdfPCell.ALIGN_CENTER, font8Normal));
            subTable.addCell(getCell(" ", PdfPCell.ALIGN_CENTER, font8Normal));

            subTable.addCell(getCell(responsableTecnico, PdfPCell.ALIGN_CENTER, font9Bold));
            subTable.addCell(getCell("Responsable Técnico", PdfPCell.ALIGN_CENTER, font8Normal));

            PdfPCell cellsub = new PdfPCell(subTable);
            cellsub.setBorder(PdfPCell.NO_BORDER);
            cellsub.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            tableDatosH3.addCell(cellsub);

            tableDatosH3.setSpacingBefore(20f);

            document.add(parphCont1);
            document.add(tableDatosH3);

            //FIN IMAGE ********************************************************************************            
            //FIN HOJA 2 *****************************************************************************************************************************
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
    }

    public class FooterTable extends PdfPageEventHelper {

        protected PdfPTable footer;

        public FooterTable(PdfPTable footer) {
            this.footer = footer;
        }

        public void onEndPage(PdfWriter writer, Document document) {
            footer.writeSelectedRows(0, -30, 30, 30, writer.getDirectContent());
        }
    }

    public PdfPCell getCell(String text, int alignment, Font fuente) {
        PdfPCell cell = new PdfPCell(new Phrase(text, fuente));
        cell.setPadding(1f);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);

        return cell;
    }

    public PdfPCell getCellFooter(String text, int alignment, Font fuente) {
        PdfPCell cell = new PdfPCell(new Phrase(text, fuente));
        cell.setPadding(2f);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);

        return cell;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
