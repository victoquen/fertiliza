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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Locale;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class Hacienda implements Serializable {

    ObjectId id;
    ObjectId idCliente;
    String nombre;
    BigDecimal hectareas;
    BigDecimal numLotes;
    List<Contacto> contactos;

    String latitud;
    String longitud;

    String leyendaCliente;

    String darBaja; //0 activos, 1 desactivados

    //List<HaciendaLoteCultivoAux> listadoLotes;

    //List<Lote> lotes;
    public Hacienda() {
        this.nombre = "";
        this.hectareas = new BigDecimal(0);
        this.numLotes = new BigDecimal(0);
        contactos = new ArrayList<>();

        this.latitud = "-2.207019";
        this.longitud = "-79.913864";
        
        /*this.latitud = "";
        this.longitud = "";*/

        this.leyendaCliente = "";
        
        this.darBaja = "0";
        //this.lotes = new ArrayList<>();
    }


    /*
    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }
     */
    public String getDarBaja() {
        return darBaja;
    }

    public void setDarBaja(String darBaja) {
        this.darBaja = darBaja;
    }

   

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getHectareas() {
        return hectareas;
    }

    public void setHectareas(BigDecimal hectareas) {
        this.hectareas = hectareas;
    }

    public BigDecimal getNumLotes() {
        return numLotes;
    }

    public void setNumLotes(BigDecimal numLotes) {
        this.numLotes = numLotes;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public ObjectId getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ObjectId idCliente) {
        this.idCliente = idCliente;
    }

    public String getLeyendaCliente() {
        return leyendaCliente;
    }

    public void setLeyendaCliente(String leyendaCliente) {
        this.leyendaCliente = leyendaCliente;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public ObjectId save() {
        List<Document> listObj = new ArrayList<>();
        MongoManager mongo = MongoManager.getInstance();

        /*List<Document> listLotes = new ArrayList<>();
        int contLotes = listadoLotes.size();
        for (int i = 0; i < contLotes; i++) {
            Document aux = new Document().append("idLotes", listadoLotes.get(i).idLotes).append("cultivo", listadoLotes.get(i).cultivo)
                    .append("variedad", listadoLotes.get(i).variedad).append("edad", listadoLotes.get(i).edad).append("hectareas", BDecimalToStr(listadoLotes.get(i).hectareas))
                    .append("codigoMayorEstacion", listadoLotes.get(i).codigoMayorEstacion)
                    .append("darBaja", listadoLotes.get(i).darBaja);

            List<Document> listPeriodos = new ArrayList<>();
            List<PeriodoMonitoreoAux> lsPeriodo = listadoLotes.get(i).getListaPeriodosMonitoreos();
            int contLsPerio = lsPeriodo.size();
            for (int l = 0; l < contLsPerio; l++) {
                Document auxPeriodo = new Document().append("numeroMonitoreo", lsPeriodo.get(l).getNumeroMonitoreo())
                        .append("numeroDias", lsPeriodo.get(l).getNumeroDias()).append("fechaMonitoreo", lsPeriodo.get(l).getFechaMonitoreo())
                        .append("pendiente", lsPeriodo.get(l).getPendiente());

                listPeriodos.add(auxPeriodo);
            }
            aux.append("listaPeriodos", listPeriodos);

            List<Document> listEstMonit = new ArrayList<>();
            List<EstacionMonitoreo> lsEstMon = listadoLotes.get(i).getListaEstacionMonitoreo();
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

            aux.append("listadoMonitoreo", listEstMonit);
            listLotes.add(aux);
        }*/

        MongoCollection table = mongo.db.getCollection("hacienda");

        Document obj = new Document("idcliente", this.idCliente)
                .append("nombre", this.nombre.toUpperCase())                
                .append("latitud", this.latitud)
                .append("longitud", this.longitud)              
                .append("darBaja", this.darBaja);
        for (int i = 0; i < contactos.size(); i++) {

            List<String> lm = contactos.get(i).email;
            StringBuilder auxmail = new StringBuilder(75);
            for (int j = 0; j < lm.size(); j++) {
                auxmail.append(lm.get(j)).append(";");
            }

            List<String> lf = contactos.get(i).telefono;
            StringBuilder auxfono = new StringBuilder(75);
            for (int k = 0; k < lf.size(); k++) {
                auxfono.append(lf.get(k)).append(";");
            }

            Document a = new Document().append("tipo", contactos.get(i).tipo).append("nombre", contactos.get(i).nombre.toUpperCase()).append("cargo", contactos.get(i).cargo.toUpperCase())
                    .append("telefono", auxfono.toString()).append("email", auxmail.toString());
            listObj.add(a);
        }

        table.insertOne(obj.append("contacto", asList(listObj)));

        return (ObjectId) obj.get("_id");

    }

    public void update() {

        /*List<Document> listLotes = new ArrayList<>();
        int contLotes = listadoLotes.size();
        for (int i = 0; i < contLotes; i++) {
            Document aux = new Document().append("idLotes", listadoLotes.get(i).idLotes).append("cultivo", listadoLotes.get(i).cultivo)
                    .append("variedad", listadoLotes.get(i).variedad).append("edad", listadoLotes.get(i).edad).append("hectareas", BDecimalToStr(listadoLotes.get(i).hectareas))
                    .append("codigoMayorEstacion", listadoLotes.get(i).codigoMayorEstacion)
                    .append("darBaja", listadoLotes.get(i).darBaja);

            List<Document> listPeriodos = new ArrayList<>();
            List<PeriodoMonitoreoAux> lsPeriodo = listadoLotes.get(i).getListaPeriodosMonitoreos();
            int contLsPerio = lsPeriodo.size();
            for (int l = 0; l < contLsPerio; l++) {
                Document auxPeriodo = new Document().append("numeroMonitoreo", lsPeriodo.get(l).getNumeroMonitoreo())
                        .append("numeroDias", lsPeriodo.get(l).getNumeroDias()).append("fechaMonitoreo", lsPeriodo.get(l).getFechaMonitoreo())
                        .append("pendiente", lsPeriodo.get(l).getPendiente());

                listPeriodos.add(auxPeriodo);
            }
            aux.append("listaPeriodos", listPeriodos);

            List<Document> listEstMonit = new ArrayList<>();
            List<EstacionMonitoreo> lsEstMon = listadoLotes.get(i).getListaEstacionMonitoreo();
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
            aux.append("listadoMonitoreo", listEstMonit);
            listLotes.add(aux);
        }*/

        List<Document> listObj = new ArrayList<>();

        for (int i = 0; i < contactos.size(); i++) {

            List<String> lm = contactos.get(i).email;
            StringBuilder auxmail = new StringBuilder(75);
            for (int j = 0; j < lm.size(); j++) {
                auxmail.append(lm.get(j)).append(";");
            }

            List<String> lf = contactos.get(i).telefono;
            StringBuilder auxfono = new StringBuilder(75);
            for (int k = 0; k < lf.size(); k++) {
                auxfono.append(lf.get(k)).append(";");
            }

            Document a = new Document().append("tipo", contactos.get(i).tipo).append("nombre", contactos.get(i).nombre.toUpperCase()).append("cargo", contactos.get(i).cargo.toUpperCase())
                    .append("telefono", auxfono.toString()).append("email", auxmail.toString());
            listObj.add(a);
        }

        //Fuente before = getFuenteById(this.id);
        MongoManager mongo = MongoManager.getInstance();
        Document obj = new Document("$set", new Document("idcliente", this.idCliente)
                .append("nombre", this.nombre.toUpperCase())                
                .append("latitud", this.latitud).append("longitud", this.longitud)
                .append("darBaja", this.darBaja)
                .append("contacto", asList(listObj)));

        mongo.db.getCollection("hacienda").updateOne(new Document("_id", this.id), obj);

    }

    public void DarDeBaja() {

        //Fuente before = getFuenteById(this.id);
        MongoManager mongo = MongoManager.getInstance();
        Document obj = new Document("$set", new Document("darBaja", "1"));

        mongo.db.getCollection("hacienda").updateOne(new Document("_id", this.id), obj);

    }

    public static Hacienda getHaciendaById(ObjectId id) {
        Hacienda obj = new Hacienda();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("hacienda").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.idCliente = (ObjectId) document.get("idcliente");
                obj.nombre = document.get("nombre").toString();                
                obj.latitud = document.get("latitud").toString();
                obj.longitud = document.get("longitud").toString();

                obj.leyendaCliente = Cliente.getClienteById(obj.idCliente).nombre;

                //obj.lotes = Lote.getAllLotesByHaciendaId(obj.id);
                List<Document> comps = (List<Document>) document.get("contacto");

                for (int i = 0; i < comps.size(); i++) {

                    List<Document> res = (List<Document>) comps.get(i);
                    for (int j = 0; j < res.size(); j++) {

                        Document dbo = (Document) res.get(j);
                        Contacto aux = new Contacto();
                        aux.tipo = dbo.get("tipo").toString();
                        aux.nombre = dbo.get("nombre").toString();
                        aux.cargo = dbo.get("cargo").toString();

                        aux.telefono = Arrays.asList(dbo.get("telefono").toString().split(";"));
                        aux.email = Arrays.asList(dbo.get("email").toString().split(";"));

                        obj.contactos.add(aux);
                    }
                }

                /*List<Document> lista = (List<Document>) document.get("listadoLotes");
                int contLista = lista.size();
                for (int i = 0; i < contLista; i++) {
                    Document dbo = (Document) lista.get(i);
                    HaciendaLoteCultivoAux aux = new HaciendaLoteCultivoAux();
                    aux.idLotes = dbo.getString("idLotes");
                    aux.cultivo = dbo.getObjectId("cultivo");
                    aux.variedad = dbo.getObjectId("variedad");
                    aux.edad = dbo.getObjectId("edad");
                    aux.darBaja = dbo.getString("darBaja");
                    aux.leyendaCultivo = Cultivo.getCultivoById(aux.cultivo).getNombre();
                    aux.leyendaVariedad = Variedad.getVariedadById(aux.variedad).getNombre();
                    aux.leyendaEdad = EtapaCultivo.getEdadById(aux.edad).getNombre();
                    aux.hectareas = obj.StrToBDecimal(dbo.getString("hectareas"));

                    aux.codigoMayorEstacion = dbo.getString("codigoMayorEstacion");

                    List<Document> listaPeriodos = (List<Document>) dbo.get("listaPeriodos");
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

                        aux.listaPeriodosMonitoreos.add(auxPeriod);
                    }

                    
                    List<Document> listaEstMonit = (List<Document>) dbo.get("listadoMonitoreo");
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

                        aux.listaEstacionMonitoreo.add(auxEstMon);
                    }

                    obj.listadoLotes.add(aux);
                }*/

            }

        });

        return obj;
    }

    public static List<Hacienda> getAllHaciendaByClienteId(ObjectId id) {
        List<Hacienda> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("hacienda").find(new Document("idcliente", id)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Hacienda obj = new Hacienda();

                obj.id = (ObjectId) document.get("_id");
                obj.idCliente = (ObjectId) document.get("idcliente");
                obj.nombre = document.get("nombre").toString();              
                obj.latitud = document.get("latitud").toString();
                obj.longitud = document.get("longitud").toString();

                obj.leyendaCliente = Cliente.getClienteById(obj.idCliente).nombre;
                //obj.lotes = Lote.getAllLotesByHaciendaId(obj.id);
                List<Document> comps = (List<Document>) document.get("contacto");

                for (int i = 0; i < comps.size(); i++) {

                    List<Document> res = (List<Document>) comps.get(i);
                    for (int j = 0; j < res.size(); j++) {

                        Document dbo = (Document) res.get(j);
                        Contacto aux = new Contacto();
                        aux.tipo = dbo.get("tipo").toString();
                        aux.nombre = dbo.get("nombre").toString();
                        aux.cargo = dbo.get("cargo").toString();

                        aux.telefono = Arrays.asList(dbo.get("telefono").toString().split(";"));
                        aux.email = Arrays.asList(dbo.get("email").toString().split(";"));
                        obj.contactos.add(aux);
                    }
                }

               /* List<Document> lista = (List<Document>) document.get("listadoLotes");
                int contLista = lista.size();
                for (int i = 0; i < contLista; i++) {
                    Document dbo = (Document) lista.get(i);
                    HaciendaLoteCultivoAux aux = new HaciendaLoteCultivoAux();
                    aux.idLotes = dbo.getString("idLotes");
                    aux.cultivo = dbo.getObjectId("cultivo");
                    aux.variedad = dbo.getObjectId("variedad");
                    aux.edad = dbo.getObjectId("edad");
                    aux.darBaja = dbo.getString("darBaja");
                    aux.leyendaCultivo = Cultivo.getCultivoById(aux.cultivo).getNombre();
                    aux.leyendaVariedad = Variedad.getVariedadById(aux.variedad).getNombre();
                    aux.leyendaEdad = EtapaCultivo.getEdadById(aux.edad).getNombre();
                    aux.hectareas = obj.StrToBDecimal(dbo.getString("hectareas"));

                    aux.codigoMayorEstacion = dbo.getString("codigoMayorEstacion");

                    List<Document> listaPeriodos = (List<Document>) dbo.get("listaPeriodos");
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

                        aux.listaPeriodosMonitoreos.add(auxPeriod);
                    }

                    
                    List<Document> listaEstMonit = (List<Document>) dbo.get("listadoMonitoreo");
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

                        aux.listaEstacionMonitoreo.add(auxEstMon);
                    }

                    obj.listadoLotes.add(aux);
                }*/

                res.add(obj);
            }

        });

        return res;
    }

    public static List<Hacienda> getAllHacienda() {
        List<Hacienda> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("hacienda").find(new Document("darBaja", "0")).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Hacienda obj = new Hacienda();

                obj.id = (ObjectId) document.get("_id");
                obj.idCliente = (ObjectId) document.get("idcliente");
                obj.nombre = document.get("nombre").toString();                
                obj.latitud = document.get("latitud").toString();
                obj.longitud = document.get("longitud").toString();

                obj.leyendaCliente = Cliente.getClienteById(obj.idCliente).nombre;
                //obj.lotes = Lote.getAllLotesByHaciendaId(obj.id);

                List<Document> comps = (List<Document>) document.get("contacto");

                for (int i = 0; i < comps.size(); i++) {

                    List<Document> res = (List<Document>) comps.get(i);
                    for (int j = 0; j < res.size(); j++) {

                        Document dbo = (Document) res.get(j);
                        Contacto aux = new Contacto();
                        aux.tipo = dbo.get("tipo").toString();
                        aux.nombre = dbo.get("nombre").toString();
                        aux.cargo = dbo.get("cargo").toString();

                        aux.telefono = Arrays.asList(dbo.get("telefono").toString().split(";"));
                        aux.email = Arrays.asList(dbo.get("email").toString().split(";"));
                        obj.contactos.add(aux);
                    }
                }

                /*List<Document> lista = (List<Document>) document.get("listadoLotes");
                int contLista = lista.size();
                for (int i = 0; i < contLista; i++) {
                    Document dbo = (Document) lista.get(i);
                    HaciendaLoteCultivoAux aux = new HaciendaLoteCultivoAux();
                    aux.idLotes = dbo.getString("idLotes");
                    aux.cultivo = dbo.getObjectId("cultivo");
                    aux.variedad = dbo.getObjectId("variedad");
                    aux.edad = dbo.getObjectId("edad");
                    aux.darBaja = dbo.getString("darBaja");
                    aux.leyendaCultivo = Cultivo.getCultivoById(aux.cultivo).getNombre();
                    aux.leyendaVariedad = Variedad.getVariedadById(aux.variedad).getNombre();
                    aux.leyendaEdad = EtapaCultivo.getEdadById(aux.edad).getNombre();
                    aux.hectareas = obj.StrToBDecimal(dbo.getString("hectareas"));

                    aux.codigoMayorEstacion = dbo.getString("codigoMayorEstacion");

                    List<Document> listaPeriodos = (List<Document>) dbo.get("listaPeriodos");
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

                        aux.listaPeriodosMonitoreos.add(auxPeriod);
                    }

                    
                    List<Document> listaEstMonit = (List<Document>) dbo.get("listadoMonitoreo");
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

                        aux.listaEstacionMonitoreo.add(auxEstMon);
                    }

                    obj.listadoLotes.add(aux);
                }*/

                res.add(obj);
            }

        });

        return res;
    }

    public static List<Hacienda> getAllHaciendaInactivos() {
        List<Hacienda> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("hacienda").find(new Document("darBaja", "1")).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Hacienda obj = new Hacienda();

                obj.id = (ObjectId) document.get("_id");
                obj.idCliente = (ObjectId) document.get("idcliente");
                obj.nombre = document.get("nombre").toString();                
                obj.latitud = document.get("latitud").toString();
                obj.longitud = document.get("longitud").toString();

                obj.leyendaCliente = Cliente.getClienteById(obj.idCliente).nombre;
                //obj.lotes = Lote.getAllLotesByHaciendaId(obj.id);

                List<Document> comps = (List<Document>) document.get("contacto");

                for (int i = 0; i < comps.size(); i++) {

                    List<Document> res = (List<Document>) comps.get(i);
                    for (int j = 0; j < res.size(); j++) {

                        Document dbo = (Document) res.get(j);
                        Contacto aux = new Contacto();
                        aux.tipo = dbo.get("tipo").toString();
                        aux.nombre = dbo.get("nombre").toString();
                        aux.cargo = dbo.get("cargo").toString();

                        aux.telefono = Arrays.asList(dbo.get("telefono").toString().split(";"));
                        aux.email = Arrays.asList(dbo.get("email").toString().split(";"));
                        obj.contactos.add(aux);
                    }
                }

                /*List<Document> lista = (List<Document>) document.get("listadoLotes");
                int contLista = lista.size();
                for (int i = 0; i < contLista; i++) {
                    Document dbo = (Document) lista.get(i);
                    HaciendaLoteCultivoAux aux = new HaciendaLoteCultivoAux();
                    aux.idLotes = dbo.getString("idLotes");
                    aux.cultivo = dbo.getObjectId("cultivo");
                    aux.variedad = dbo.getObjectId("variedad");
                    aux.edad = dbo.getObjectId("edad");
                    aux.darBaja = dbo.getString("darBaja");
                    aux.leyendaCultivo = Cultivo.getCultivoById(aux.cultivo).getNombre();
                    aux.leyendaVariedad = Variedad.getVariedadById(aux.variedad).getNombre();
                    aux.leyendaEdad = EtapaCultivo.getEdadById(aux.edad).getNombre();
                    aux.hectareas = obj.StrToBDecimal(dbo.getString("hectareas"));

                    aux.codigoMayorEstacion = dbo.getString("codigoMayorEstacion");

                    List<Document> listaPeriodos = (List<Document>) dbo.get("listaPeriodos");
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

                        aux.listaPeriodosMonitoreos.add(auxPeriod);
                    }

                    
                    List<Document> listaEstMonit = (List<Document>) dbo.get("listadoMonitoreo");
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

                        aux.listaEstacionMonitoreo.add(auxEstMon);
                    }

                    obj.listadoLotes.add(aux);
                }*/

                res.add(obj);
            }

        });

        return res;
    }

    public static HaciendaLoteCultivoAux getHaciendaLoteCultivoByIdLotes(List<HaciendaLoteCultivoAux> lista, String idLote) {
        HaciendaLoteCultivoAux res = null;

        int cont = 0;
        int sizeLista = lista.size();
        while (cont < sizeLista) {
            if (lista.get(cont).idLotes.equals(idLote)) {
                res = lista.get(cont);
                cont = sizeLista;
            }
            cont++;
        }

        return res;
    }


    
    public static HaciendaLoteCultivoAux getPeriodoMonitoreoAuxByIdEstacionCategoriaMayor(List<HaciendaLoteCultivoAux> lista, String codEstacion) {
        HaciendaLoteCultivoAux res = null;

        int cont = 0;
        int sizeLista = lista.size();
        while (cont < sizeLista) {
            if (lista.get(cont).codigoMayorEstacion.equals(codEstacion)) {
                res = lista.get(cont);
                res.ListaPeriodosMonitoreosPendientes();
                cont = sizeLista;
            }
            cont++;
        }

        return res;
    }

    String BDecimalToStr(BigDecimal arg) {
        String res;
        res = arg.toString();
        return res;
    }

    BigDecimal StrToBDecimal(String arg) {
        BigDecimal res;
        res = new BigDecimal(arg);
        return res;
    }

}
