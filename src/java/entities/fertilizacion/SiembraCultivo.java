/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import db.MongoManager;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class SiembraCultivo implements Serializable {

    ObjectId id;
    ObjectId idHacienda;
    ObjectId idCultivo;
    ObjectId idVariedad;
    String codigo;

    Date fechaSiembra;
    String fechaSiembraFormat;

    String estado; //activo - finalizado

    String leyendaCultivo;
    String leyendaVariedad;
    String leyendaEtapaCultivo;
    String leyendaHacienda;
    String leyendaCliente;
    

    List<LoteSiembraAux> listaLotesSiembra;

    String unidadManejo;
    List<PeriodoMonitoreoAux> listaPeriodosMonitoreos;
    List<PeriodoMonitoreoAux> listaPeriodosMonitoreosPendiente;
    List<EstacionMonitoreo> listaEstacionMonitoreo;

    public SiembraCultivo() {
        this.fechaSiembraFormat = "";
        this.leyendaCultivo = "";
        this.leyendaVariedad = "";
        this.leyendaHacienda = "";
        this.leyendaCliente = "";
        this.leyendaEtapaCultivo = "";

        this.codigo = "";
        this.listaLotesSiembra = new ArrayList<>();

        this.unidadManejo = "";
        this.listaPeriodosMonitoreos = new ArrayList<>();
        this.listaPeriodosMonitoreosPendiente = new ArrayList<>();
        this.listaEstacionMonitoreo = new ArrayList<>();

        this.estado = "activo";
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ObjectId getIdHacienda() {
        return idHacienda;
    }

    public void setIdHacienda(ObjectId idHacienda) {
        this.idHacienda = idHacienda;
    }

    public ObjectId getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(ObjectId idCultivo) {
        this.idCultivo = idCultivo;
    }

    public ObjectId getIdVariedad() {
        return idVariedad;
    }

    public void setIdVariedad(ObjectId idVariedad) {
        this.idVariedad = idVariedad;
    }

    public Date getFechaSiembra() {
        return fechaSiembra;
    }

    public void setFechaSiembra(Date fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public String getFechaSiembraFormat() {
        return fechaSiembraFormat;
    }

    public void setFechaSiembraFormat(String fechaSiembraFormat) {
        this.fechaSiembraFormat = fechaSiembraFormat;
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    public String getLeyendaVariedad() {
        return leyendaVariedad;
    }

    public void setLeyendaVariedad(String leyendaVariedad) {
        this.leyendaVariedad = leyendaVariedad;
    }

    public String getLeyendaHacienda() {
        return leyendaHacienda;
    }

    public void setLeyendaHacienda(String leyendaHacienda) {
        this.leyendaHacienda = leyendaHacienda;
    }

    public String getLeyendaCliente() {
        return leyendaCliente;
    }

    public void setLeyendaCliente(String leyendaCliente) {
        this.leyendaCliente = leyendaCliente;
    }

    public List<LoteSiembraAux> getListaLotesSiembra() {
        return listaLotesSiembra;
    }

    public void setListaLotesSiembra(List<LoteSiembraAux> listaLotesSiembra) {
        this.listaLotesSiembra = listaLotesSiembra;
    }

    public String getUnidadManejo() {
        return unidadManejo;
    }

    public void setUnidadManejo(String unidadManejo) {
        this.unidadManejo = unidadManejo;
    }

    public List<PeriodoMonitoreoAux> getListaPeriodosMonitoreos() {
        return listaPeriodosMonitoreos;
    }

    public void setListaPeriodosMonitoreos(List<PeriodoMonitoreoAux> listaPeriodosMonitoreos) {
        this.listaPeriodosMonitoreos = listaPeriodosMonitoreos;
    }

    public List<PeriodoMonitoreoAux> getListaPeriodosMonitoreosPendiente() {
        return listaPeriodosMonitoreosPendiente;
    }

    public void setListaPeriodosMonitoreosPendiente(List<PeriodoMonitoreoAux> listaPeriodosMonitoreosPendiente) {
        this.listaPeriodosMonitoreosPendiente = listaPeriodosMonitoreosPendiente;
    }

    public List<EstacionMonitoreo> getListaEstacionMonitoreo() {
        return listaEstacionMonitoreo;
    }

    public void setListaEstacionMonitoreo(List<EstacionMonitoreo> listaEstacionMonitoreo) {
        this.listaEstacionMonitoreo = listaEstacionMonitoreo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLeyendaEtapaCultivo() {
        return leyendaEtapaCultivo;
    }

    public void setLeyendaEtapaCultivo(String leyendaEtapaCultivo) {
        this.leyendaEtapaCultivo = leyendaEtapaCultivo;
    }

    
    
    public void ListaPeriodosMonitoreosPendientes() {
        int sizeLista = listaPeriodosMonitoreos.size();
        for (int i = 0; i < sizeLista; i++) {
            if (listaPeriodosMonitoreos.get(i).getPendiente().equals("pendiente")) {
                listaPeriodosMonitoreosPendiente.add(listaPeriodosMonitoreos.get(i));
            }
        }
    }

    public ObjectId save() {

        List<Document> listLotes = new ArrayList<>();
        List<LoteSiembraAux> lsLoteSiembra = this.listaLotesSiembra;
        int contLsLoteSiem = lsLoteSiembra.size();
        for (int i = 0; i < contLsLoteSiem; i++) {
            Document auxLoteS = new Document().append("idLote", lsLoteSiembra.get(i).getIdLote())
                    .append("estado", lsLoteSiembra.get(i).getEstado());

            listLotes.add(auxLoteS);
        }

        List<Document> listPeriodos = new ArrayList<>();
        List<PeriodoMonitoreoAux> lsPeriodo = this.listaPeriodosMonitoreos;
        int contLsPerio = lsPeriodo.size();
        for (int l = 0; l < contLsPerio; l++) {
            Document auxPeriodo = new Document().append("numeroMonitoreo", lsPeriodo.get(l).getNumeroMonitoreo())
                    .append("numeroDias", lsPeriodo.get(l).getNumeroDias())
                    .append("fechaMonitoreo", lsPeriodo.get(l).getFechaMonitoreo())
                    .append("pendiente", lsPeriodo.get(l).getPendiente());

            listPeriodos.add(auxPeriodo);
        }

        List<Document> listEstMonit = new ArrayList<>();
        List<EstacionMonitoreo> lsEstMon = this.listaEstacionMonitoreo;
        int contLsEstMon = lsEstMon.size();
        for (int j = 0; j < contLsEstMon; j++) {
            Document auxEstMon = new Document().append("codigo", lsEstMon.get(j).getCodigo()).append("latitudestacion", lsEstMon.get(j).getLatitudestacion())
                    .append("longitudestacion", lsEstMon.get(j).getLongitudestacion());

            List<Document> listSondas = new ArrayList<>();
            List<SondaAux> lsSonda = lsEstMon.get(j).getListaSondas();
            int contLsSonda = lsSonda.size();
            for (int k = 0; k < contLsSonda; k++) {
                Document auxSonda = new Document().append("tipoSonda", lsSonda.get(k).getTipoSonda()).append("descripcion", lsSonda.get(k).getDescripcion())
                        .append("profundidad", lsSonda.get(k).getProfundidad());

                listSondas.add(auxSonda);
            }
            auxEstMon.append("listaSondas", listSondas);

            listEstMonit.add(auxEstMon);
        }

        MongoManager mongo = MongoManager.getInstance();
        MongoCollection table = mongo.db.getCollection("siembraCultivo");

        Document obj = new Document("idHacienda", this.idHacienda)
                .append("idCultivo", this.idCultivo)
                .append("idVariedad", this.idVariedad)
                .append("codigo", this.codigo)
                .append("fechaSiembra", this.fechaSiembra)
                .append("estado", this.estado)
                .append("unidadManejo", this.unidadManejo)
                .append("listaPeriodos", listPeriodos)
                .append("listadoMonitoreo", listEstMonit)
                .append("listadoLotes", listLotes);

        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        List<Document> listLotes = new ArrayList<>();
        List<LoteSiembraAux> lsLoteSiembra = this.listaLotesSiembra;
        int contLsLoteSiem = lsLoteSiembra.size();
        for (int i = 0; i < contLsLoteSiem; i++) {
            Document auxLoteS = new Document().append("idLote", lsLoteSiembra.get(i).getIdLote())
                    .append("estado", lsLoteSiembra.get(i).getEstado());

            listLotes.add(auxLoteS);
        }

        List<Document> listPeriodos = new ArrayList<>();
        List<PeriodoMonitoreoAux> lsPeriodo = this.listaPeriodosMonitoreos;
        int contLsPerio = lsPeriodo.size();
        for (int l = 0; l < contLsPerio; l++) {
            Document auxPeriodo = new Document().append("numeroMonitoreo", lsPeriodo.get(l).getNumeroMonitoreo())
                    .append("numeroDias", lsPeriodo.get(l).getNumeroDias()).append("fechaMonitoreo", lsPeriodo.get(l).getFechaMonitoreo())
                    .append("pendiente", lsPeriodo.get(l).getPendiente());

            listPeriodos.add(auxPeriodo);
        }

        List<Document> listEstMonit = new ArrayList<>();
        List<EstacionMonitoreo> lsEstMon = this.listaEstacionMonitoreo;
        int contLsEstMon = lsEstMon.size();
        for (int j = 0; j < contLsEstMon; j++) {
            Document auxEstMon = new Document().append("codigo", lsEstMon.get(j).getCodigo()).append("latitudestacion", lsEstMon.get(j).getLatitudestacion())
                    .append("longitudestacion", lsEstMon.get(j).getLongitudestacion());

            List<Document> listSondas = new ArrayList<>();
            List<SondaAux> lsSonda = lsEstMon.get(j).getListaSondas();
            int contLsSonda = lsSonda.size();
            for (int k = 0; k < contLsSonda; k++) {
                Document auxSonda = new Document().append("tipoSonda", lsSonda.get(k).getTipoSonda()).append("descripcion", lsSonda.get(k).getDescripcion())
                        .append("profundidad", lsSonda.get(k).getProfundidad());

                listSondas.add(auxSonda);
            }
            auxEstMon.append("listaSondas", listSondas);

            listEstMonit.add(auxEstMon);
        }

        MongoManager mongo = MongoManager.getInstance();
        Document obj = new Document("$set", new Document("idHacienda", this.idHacienda)
                .append("idCultivo", this.idCultivo)
                .append("idVariedad", this.idVariedad)
                .append("codigo", this.codigo)
                .append("fechaSiembra", this.fechaSiembra)
                .append("estado", this.estado)
                .append("unidadManejo", this.unidadManejo)
                .append("listaPeriodos", listPeriodos)
                .append("listadoMonitoreo", listEstMonit))
                .append("listadoLotes", listLotes);

        mongo.db.getCollection("siembraCultivo").updateOne(new Document("_id", this.id), obj);
    }

    public void finalizar() {
        MongoManager mongo = MongoManager.getInstance();
        Document obj = new Document("$set", new Document("estado", "finalizado"));
        mongo.db.getCollection("siembraCultivo").updateOne(new Document("_id", this.id), obj);
    }

    public static SiembraCultivo getById(ObjectId id) {
        SiembraCultivo obj = new SiembraCultivo();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("siembraCultivo").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = document.getObjectId("_id");
                obj.idHacienda = document.getObjectId("idHacienda");
                obj.idCultivo = document.getObjectId("idCultivo");
                obj.idVariedad = document.getObjectId("idVariedad");
                obj.codigo = document.getString("codigo");
                obj.fechaSiembra = document.getDate("fechaSiembra");
                obj.estado = document.getString("estado");
                obj.unidadManejo = document.getString("unidadManejo");

                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).getNombre();
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).getNombre();
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).getNombre();
                obj.leyendaCliente = Hacienda.getHaciendaById(obj.idHacienda).getLeyendaCliente();

                if (obj.fechaSiembra != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaSiembraFormat = formateadorRec.format(obj.fechaSiembra);
                } else {
                    obj.fechaSiembraFormat = "---";
                }

                List<Document> listaLotes = (List<Document>) document.get("listadoLotes");
                int contListaLotes = listaLotes.size();
                for (int k = 0; k < contListaLotes; k++) {
                    Document dboLote = (Document) listaLotes.get(k);
                    LoteSiembraAux auxLote = new LoteSiembraAux();
                    auxLote.idLote = dboLote.getObjectId("idLote");
                    auxLote.estado = dboLote.getString("estado");
                    auxLote.leyendaLote = Lote.getLoteById(auxLote.idLote).getCodigo() + "(" + Lote.getLoteById(auxLote.idLote).getHectareas() + " hctas.)";

                    obj.listaLotesSiembra.add(auxLote);

                }

                List<Document> listaPeriodos = (List<Document>) document.get("listaPeriodos");
                int contListaPerio = listaPeriodos.size();
                for (int l = 0; l < contListaPerio; l++) {
                    Document dboPeriodo = (Document) listaPeriodos.get(l);
                    PeriodoMonitoreoAux auxPeriod = new PeriodoMonitoreoAux();
                    auxPeriod.numeroMonitoreo = dboPeriodo.getInteger("numeroMonitoreo");
                    auxPeriod.numeroDias = dboPeriodo.getInteger("numeroDias");
                    auxPeriod.fechaMonitoreo = dboPeriodo.getDate("fechaMonitoreo");
                    auxPeriod.pendiente = dboPeriodo.getString("pendiente");

                    if (auxPeriod.fechaMonitoreo != null) {
                        SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                        auxPeriod.fechaMonitoreoFormat = formateadorRec.format(auxPeriod.fechaMonitoreo);
                    } else {
                        auxPeriod.fechaMonitoreoFormat = "---";
                    }

                    obj.listaPeriodosMonitoreos.add(auxPeriod);

                }

                List<Document> listaEstMonit = (List<Document>) document.get("listadoMonitoreo");
                int contListaEstMonit = listaEstMonit.size();
                for (int j = 0; j < contListaEstMonit; j++) {
                    Document dboEstMon = (Document) listaEstMonit.get(j);
                    EstacionMonitoreo auxEstMon = new EstacionMonitoreo();
                    auxEstMon.codigo = dboEstMon.getString("codigo");
                    auxEstMon.latitudestacion = dboEstMon.getString("latitudestacion");
                    auxEstMon.longitudestacion = dboEstMon.getString("longitudestacion");

                    List<Document> listaSondas = (List<Document>) dboEstMon.get("listaSondas");
                    int contListaSonda = listaSondas.size();
                    for (int k = 0; k < contListaSonda; k++) {
                        Document dboSonda = (Document) listaSondas.get(k);
                        SondaAux auxSonda = new SondaAux();
                        auxSonda.tipoSonda = dboSonda.getObjectId("tipoSonda");
                        auxSonda.descripcion = dboSonda.getString("descripcion");
                        auxSonda.leyendaTipoSonda = SondaTipo.getSondaTipoById(auxSonda.tipoSonda).getNombre();
                        auxSonda.profundidad = dboSonda.getObjectId("profundidad");
                        auxSonda.leyendaProfundidad = Profundidad.getById(auxSonda.profundidad).getNombre();

                        auxEstMon.listaSondas.add(auxSonda);
                    }

                    obj.listaEstacionMonitoreo.add(auxEstMon);
                }

            }

        });

        return obj;
    }

    public static List<SiembraCultivo> getAll() {
        List<SiembraCultivo> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("siembraCultivo").find(new Document("estado", "activo")).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                SiembraCultivo obj = new SiembraCultivo();

                obj.id = document.getObjectId("_id");
                obj.idHacienda = document.getObjectId("idHacienda");
                obj.idCultivo = document.getObjectId("idCultivo");
                obj.idVariedad = document.getObjectId("idVariedad");
                obj.codigo = document.getString("codigo");
                obj.fechaSiembra = document.getDate("fechaSiembra");
                obj.estado = document.getString("estado");
                obj.unidadManejo = document.getString("unidadManejo");

                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).getNombre();
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).getNombre();
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).getNombre();
                obj.leyendaCliente = Hacienda.getHaciendaById(obj.idHacienda).getLeyendaCliente();

                if (obj.fechaSiembra != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaSiembraFormat = formateadorRec.format(obj.fechaSiembra);
                } else {
                    obj.fechaSiembraFormat = "---";
                }

                List<Document> listaLotes = (List<Document>) document.get("listadoLotes");
                int contListaLotes = listaLotes.size();
                for (int k = 0; k < contListaLotes; k++) {
                    Document dboLote = (Document) listaLotes.get(k);
                    LoteSiembraAux auxLote = new LoteSiembraAux();
                    auxLote.idLote = dboLote.getObjectId("idLote");
                    auxLote.estado = dboLote.getString("estado");
                    auxLote.leyendaLote = Lote.getLoteById(auxLote.idLote).getCodigo() + "(" + Lote.getLoteById(auxLote.idLote).getHectareas() + " hctas.)";

                    obj.listaLotesSiembra.add(auxLote);

                }

                List<Document> listaPeriodos = (List<Document>) document.get("listaPeriodos");
                int contListaPerio = listaPeriodos.size();
                for (int l = 0; l < contListaPerio; l++) {
                    Document dboPeriodo = (Document) listaPeriodos.get(l);
                    PeriodoMonitoreoAux auxPeriod = new PeriodoMonitoreoAux();
                    auxPeriod.numeroMonitoreo = dboPeriodo.getInteger("numeroMonitoreo");
                    auxPeriod.numeroDias = dboPeriodo.getInteger("numeroDias");
                    auxPeriod.fechaMonitoreo = dboPeriodo.getDate("fechaMonitoreo");
                    auxPeriod.pendiente = dboPeriodo.getString("pendiente");

                    if (auxPeriod.fechaMonitoreo != null) {
                        SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                        auxPeriod.fechaMonitoreoFormat = formateadorRec.format(auxPeriod.fechaMonitoreo);
                    } else {
                        auxPeriod.fechaMonitoreoFormat = "---";
                    }

                    obj.listaPeriodosMonitoreos.add(auxPeriod);

                }

                List<Document> listaEstMonit = (List<Document>) document.get("listadoMonitoreo");
                int contListaEstMonit = listaEstMonit.size();
                for (int j = 0; j < contListaEstMonit; j++) {
                    Document dboEstMon = (Document) listaEstMonit.get(j);
                    EstacionMonitoreo auxEstMon = new EstacionMonitoreo();
                    auxEstMon.codigo = dboEstMon.getString("codigo");
                    auxEstMon.latitudestacion = dboEstMon.getString("latitudestacion");
                    auxEstMon.longitudestacion = dboEstMon.getString("longitudestacion");

                    List<Document> listaSondas = (List<Document>) dboEstMon.get("listaSondas");
                    int contListaSonda = listaSondas.size();
                    for (int k = 0; k < contListaSonda; k++) {
                        Document dboSonda = (Document) listaSondas.get(k);
                        SondaAux auxSonda = new SondaAux();
                        auxSonda.tipoSonda = dboSonda.getObjectId("tipoSonda");
                        auxSonda.descripcion = dboSonda.getString("descripcion");
                        auxSonda.leyendaTipoSonda = SondaTipo.getSondaTipoById(auxSonda.tipoSonda).getNombre();
                        auxSonda.profundidad = dboSonda.getObjectId("profundidad");
                        auxSonda.leyendaProfundidad = Profundidad.getById(auxSonda.profundidad).getNombre();

                        auxEstMon.listaSondas.add(auxSonda);
                    }

                    obj.listaEstacionMonitoreo.add(auxEstMon);
                }
                
                res.add(obj);
            }

        });

        return res;
    }

    

    

}
