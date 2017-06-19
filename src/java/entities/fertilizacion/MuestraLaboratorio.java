/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.ascending;
import db.MongoManager;
import helpers.Canton;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class MuestraLaboratorio implements Serializable {

    ObjectId id;
    ObjectId matriz;
    ObjectId courier;
    String trackerNumber;

    String codigo;
    int numeroCodigo;

    ObjectId cliente;
//    ObjectId hacienda;
//    String lote;
//    HaciendaLoteCultivoAux loteCompleto;
//    ObjectId cultivo;


    ObjectId idSiembraCultivo;//reemplazando string lote, cliente, hacienda, loteCompleto, cultivo
    SiembraCultivo siembraCultivo;

    String tipoMuestra;
    String estacionMonitoreo;
    int numeroMonitoreo;

    String personaContacto;
    String emailInforme;
    String direccionInforme;
    ObjectId canton;
    String productor;
    String muestreador;
    Date fechaMuestreo;
    Date fechaEnvio;
    Date fechaCreacion;

    String fechaFormatMuestreo;
    String fechaFormatEnvio;
    String fechaFormatCreacion;

    List<AnalisisLaboratorio> listadoPaquetesoAux;
    List<Subanalisis> listadoSubanalisisAux;

    String observaciones;
    ObjectId departamento;
    String estadoMuestra; //ANALISIS - RESULTADO

    String leyendaCliente;
    String leyendaHacienda;
    String leyendaLote;
    String leyendaCultivo;
    String leyendaCanton;
    String leyendaMatriz;
    String leyendaCourier;
    String leyendaDepartamento;

    public MuestraLaboratorio() {
        this.codigo = "";
        this.personaContacto = "";
        this.emailInforme = "";
        this.direccionInforme = "";

        this.productor = "";
        this.muestreador = "";

        this.listadoPaquetesoAux = new ArrayList<>();
        this.listadoSubanalisisAux = new ArrayList<>();

        this.observaciones = "";
        this.leyendaCliente = "";
        this.leyendaHacienda = "";
        this.leyendaLote = "";
        this.leyendaCultivo = "";

        this.trackerNumber = "";
        this.leyendaMatriz = "";
        this.leyendaCourier = "";
        this.tipoMuestra = "";

        this.fechaCreacion = new Date();
        this.leyendaDepartamento = "";
        this.numeroCodigo = 0;
        this.estadoMuestra = "ANALISIS";
    }

    public ObjectId getCliente() {
        return cliente;
    }

    public void setCliente(ObjectId cliente) {
        this.cliente = cliente;
    }

  

    public ObjectId getIdSiembraCultivo() {
        return idSiembraCultivo;
    }

    public void setIdSiembraCultivo(ObjectId idSiembraCultivo) {
        this.idSiembraCultivo = idSiembraCultivo;
    }

    public SiembraCultivo getSiembraCultivo() {
        return siembraCultivo;
    }

    public void setSiembraCultivo(SiembraCultivo siembraCultivo) {
        this.siembraCultivo = siembraCultivo;
    }

    public String getEstadoMuestra() {
        return estadoMuestra;
    }

    public void setEstadoMuestra(String estadoMuestra) {
        this.estadoMuestra = estadoMuestra;
    }

    public String getLeyendaMatriz() {
        return leyendaMatriz;
    }

    public void setLeyendaMatriz(String leyendaMatriz) {
        this.leyendaMatriz = leyendaMatriz;
    }

    public String getLeyendaCourier() {
        return leyendaCourier;
    }

    public void setLeyendaCourier(String leyendaCourier) {
        this.leyendaCourier = leyendaCourier;
    }

    public int getNumeroCodigo() {
        return numeroCodigo;
    }

    public void setNumeroCodigo(int numeroCodigo) {
        this.numeroCodigo = numeroCodigo;
    }

    public String getEstacionMonitoreo() {
        return estacionMonitoreo;
    }

    public void setEstacionMonitoreo(String estacionMonitoreo) {
        this.estacionMonitoreo = estacionMonitoreo;
    }

    public int getNumeroMonitoreo() {
        return numeroMonitoreo;
    }

    public void setNumeroMonitoreo(int numeroMonitoreo) {
        this.numeroMonitoreo = numeroMonitoreo;
    }

    

    public String getLeyendaDepartamento() {
        return leyendaDepartamento;
    }

    public void setLeyendaDepartamento(String leyendaDepartamento) {
        this.leyendaDepartamento = leyendaDepartamento;
    }

    public ObjectId getDepartamento() {
        return departamento;
    }

    public void setDepartamento(ObjectId departamento) {
        this.departamento = departamento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaFormatCreacion() {
        return fechaFormatCreacion;
    }

    public void setFechaFormatCreacion(String fechaFormatCreacion) {
        this.fechaFormatCreacion = fechaFormatCreacion;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public String getTrackerNumber() {
        return trackerNumber;
    }

    public void setTrackerNumber(String trackerNumber) {
        this.trackerNumber = trackerNumber;
    }

    public ObjectId getMatriz() {
        return matriz;
    }

    public void setMatriz(ObjectId matriz) {
        this.matriz = matriz;
    }

    public ObjectId getCourier() {
        return courier;
    }

    public void setCourier(ObjectId courier) {
        this.courier = courier;
    }

    public List<AnalisisLaboratorio> getListadoPaquetesoAux() {
        return listadoPaquetesoAux;
    }

    public void setListadoPaquetesoAux(List<AnalisisLaboratorio> listadoPaquetesoAux) {
        this.listadoPaquetesoAux = listadoPaquetesoAux;
    }

    public List<Subanalisis> getListadoSubanalisisAux() {
        return listadoSubanalisisAux;
    }

    public void setListadoSubanalisisAux(List<Subanalisis> listadoSubanalisisAux) {
        this.listadoSubanalisisAux = listadoSubanalisisAux;
    }

    public String getFechaFormatMuestreo() {
        return fechaFormatMuestreo;
    }

    public void setFechaFormatMuestreo(String fechaFormatMuestreo) {
        this.fechaFormatMuestreo = fechaFormatMuestreo;
    }

    public String getFechaFormatEnvio() {
        return fechaFormatEnvio;
    }

    public void setFechaFormatEnvio(String fechaFormatEnvio) {
        this.fechaFormatEnvio = fechaFormatEnvio;
    }

    public ObjectId getCanton() {
        return canton;
    }

    public void setCanton(ObjectId canton) {
        this.canton = canton;
    }

    public String getLeyendaCanton() {
        return leyendaCanton;
    }

    public void setLeyendaCanton(String leyendaCanton) {
        this.leyendaCanton = leyendaCanton;
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

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getEmailInforme() {
        return emailInforme;
    }

    public void setEmailInforme(String emailInforme) {
        this.emailInforme = emailInforme;
    }

    public String getDireccionInforme() {
        return direccionInforme;
    }

    public void setDireccionInforme(String direccionInforme) {
        this.direccionInforme = direccionInforme;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getMuestreador() {
        return muestreador;
    }

    public void setMuestreador(String muestreador) {
        this.muestreador = muestreador;
    }

    public Date getFechaMuestreo() {
        return fechaMuestreo;
    }

    public void setFechaMuestreo(Date fechaMuestreo) {
        this.fechaMuestreo = fechaMuestreo;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getLeyendaCliente() {
        return leyendaCliente;
    }

    public void setLeyendaCliente(String leyendaCliente) {
        this.leyendaCliente = leyendaCliente;
    }

    public String getLeyendaHacienda() {
        return leyendaHacienda;
    }

    public void setLeyendaHacienda(String leyendaHacienda) {
        this.leyendaHacienda = leyendaHacienda;
    }

    public String getLeyendaLote() {
        return leyendaLote;
    }

    public void setLeyendaLote(String leyendaLote) {
        this.leyendaLote = leyendaLote;
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    ObjectId idLaboratorioByIdAnalisis(List<AnalisisLaboratorioAux> lista, ObjectId idAnalis) {
        ObjectId res = null;
        int cont = 0;
        while (cont < lista.size()) {
            if (lista.get(cont).idAnalisis.equals(idAnalis)) {
                res = lista.get(cont).idLaboratorio;
                cont = lista.size();
            }
            cont++;
        }

        return res;
    }

    public void controlDates() {
        if (this.fechaEnvio == null) {
            this.fechaEnvio = new Date(0000, 00, 00);
        }
        if (this.fechaEnvio == null) {
            this.fechaEnvio = new Date();
        }
    }

    List<Document> listadoPaqueteAnalisis() {

        List<Document> listObj = new ArrayList<>();

        for (int i = 0; i < this.listadoPaquetesoAux.size(); i++) {
            Document obj = new Document().append("idanalisis", this.listadoPaquetesoAux.get(i).getId()).append("idlaboratorio", this.listadoPaquetesoAux.get(i).getLaboratorio());
            listObj.add(obj);
        }

        return listObj;
    }

    List<Document> listadoSubanalisis() {

        List<Document> listObj = new ArrayList<>();

        for (int i = 0; i < this.listadoSubanalisisAux.size(); i++) {
            Document obj = new Document().append("idanalisis", this.listadoSubanalisisAux.get(i).getId()).append("idlaboratorio", this.listadoSubanalisisAux.get(i).getLaboratorio());
            listObj.add(obj);
        }

        return listObj;
    }

    public ObjectId save() {

        MongoManager mongo = MongoManager.getInstance();
        MongoCollection table = mongo.db.getCollection("muestralaboratorio");

        List<Document> listObjSubanalisis = listadoSubanalisis();
        List<Document> listObjPaquete = listadoPaqueteAnalisis();

        //.append("hacienda", this.hacienda)
        //.append("lote", this.lote)
        //.append("cultivo", this.cultivo)
        Document obj = new Document("codigo", this.codigo.toUpperCase())
                .append("cliente", this.cliente)
                .append("idSiembraCultivo", this.idSiembraCultivo)
                .append("personacontacto", this.personaContacto)
                .append("emailinforme", this.emailInforme)
                .append("direccioninforme", this.direccionInforme)
                .append("canton", this.canton)
                .append("productor", this.productor)
                .append("muestreador", this.muestreador.toUpperCase())
                .append("fechamuestreo", this.fechaMuestreo)
                .append("fechaenvio", this.fechaEnvio)
                .append("observaciones", this.observaciones)
                .append("matriz", this.matriz)
                .append("courier", this.courier)
                .append("trackerNumber", this.trackerNumber)
                .append("tipoMuestra", this.tipoMuestra)
                .append("fechaCreacion", this.fechaCreacion)
                .append("departamento", this.departamento)
                .append("estacionMonitoreo", this.estacionMonitoreo)
                .append("numeroMonitoreo", this.numeroMonitoreo)
                .append("numeroCodigo", this.numeroCodigo)
                .append("estadoMuestra", this.estadoMuestra)
                .append("subanalisis", listObjSubanalisis)
                .append("paquetes", listObjPaquete);

        table.insertOne(obj);
        return (ObjectId) obj.get("_id");
    }

    public void update() {

        MuestraLaboratorio before = getMuestraLaboratorioById(this.id);
        MongoManager mongo = MongoManager.getInstance();

        List<Document> listObjSubanalisis = listadoSubanalisis();
        List<Document> listObjPaquete = listadoPaqueteAnalisis();
        
        //.append("hacienda", this.hacienda)
        //.append("lote", this.lote)
        //.append("cultivo", this.cultivo)
        Document obj = new Document("$set", new Document("codigo", this.codigo.toUpperCase())
                .append("cliente", this.cliente)
                .append("idSiembraCultivo", this.idSiembraCultivo)
                .append("personacontacto", this.personaContacto)
                .append("emailinforme", this.emailInforme)
                .append("direccioninforme", this.direccionInforme)
                .append("canton", this.canton)
                .append("productor", this.productor)
                .append("muestreador", this.muestreador.toUpperCase())
                .append("fechamuestreo", this.fechaMuestreo)
                .append("fechaenvio", this.fechaEnvio)
                .append("observaciones", this.observaciones)
                .append("matriz", this.matriz)
                .append("courier", this.courier)
                .append("trackerNumber", this.trackerNumber)
                .append("tipoMuestra", this.tipoMuestra)
                .append("fechaCreacion", this.fechaCreacion)
                .append("departamento", this.departamento)
                .append("estacionMonitoreo", this.estacionMonitoreo)
                .append("numeroMonitoreo", this.numeroMonitoreo)
                .append("numeroCodigo", this.numeroCodigo)
                .append("estadoMuestra", this.estadoMuestra)
                .append("subanalisis", listObjSubanalisis)
                .append("paquetes", listObjPaquete)
        );

        mongo.db.getCollection("muestralaboratorio").updateOne(new Document("_id", this.id), obj);

    }

    public static MuestraLaboratorio getMuestraLaboratorioByCodigo(String code) {
        MuestraLaboratorio obj = new MuestraLaboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("muestralaboratorio").find(new Document("nombre", code));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.cliente = (ObjectId) document.get("cliente");
//                obj.hacienda = (ObjectId) document.get("hacienda");
//                obj.lote = document.getString("lote");
//                obj.cultivo = (ObjectId) document.get("cultivo");
                obj.idSiembraCultivo = document.getObjectId("idSiembraCultivo");
                obj.siembraCultivo = SiembraCultivo.getById(obj.idSiembraCultivo);
                obj.codigo = document.get("codigo").toString();
                obj.personaContacto = document.get("personacontacto").toString();
                obj.emailInforme = document.get("emailinforme").toString();
                obj.direccionInforme = document.get("direccioninforme").toString();
                obj.canton = (ObjectId) document.get("canton");
                obj.productor = document.get("productor").toString();
                obj.muestreador = document.get("muestreador").toString();
                obj.observaciones = document.get("observaciones").toString();
                obj.fechaMuestreo = (Date) document.getDate("fechamuestreo");
                obj.fechaEnvio = (Date) document.getDate("fechaenvio");

                obj.matriz = document.getObjectId("matriz");
                obj.courier = document.getObjectId("courier");
                obj.trackerNumber = document.getString("trackerNumber");
                obj.tipoMuestra = document.getString("tipoMuestra");
                obj.fechaCreacion = document.getDate("fechaCreacion");
                obj.departamento = document.getObjectId("departamento");
                obj.leyendaDepartamento = Departamento.getById(obj.departamento).getNombre();
                obj.estacionMonitoreo = document.getString("estacionMonitoreo");
                obj.numeroMonitoreo = document.getInteger("numeroMonitoreo");
                obj.numeroCodigo = document.getInteger("numeroCodigo");
                obj.estadoMuestra = document.getString("estadoMuestra");

                if (obj.fechaCreacion != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatCreacion = formateadorRec.format(obj.fechaCreacion);
                } else {
                    obj.fechaFormatCreacion = "---";
                }

                if (obj.fechaMuestreo != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatMuestreo = formateadorRec.format(obj.fechaMuestreo);
                } else {
                    obj.fechaFormatMuestreo = "---";
                }

                if (obj.fechaEnvio != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatEnvio = formateadorRec.format(obj.fechaEnvio);
                } else {
                    obj.fechaFormatEnvio = "---";
                }

                List<Document> paquetes = (List<Document>) document.get("paquetes");
                List<Document> subana = (List<Document>) document.get("subanalisis");

                for (int i = 0; i < paquetes.size(); i++) {
                    Document dbo = (Document) paquetes.get(i);

                    AnalisisLaboratorio al = AnalisisLaboratorio.getAnalisisLaboratorioById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoPaquetesoAux.add(al);

                }

                for (int i = 0; i < subana.size(); i++) {
                    Document dbo = (Document) subana.get(i);

                    Subanalisis al = Subanalisis.getById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoSubanalisisAux.add(al);

                }

//                obj.loteCompleto = Hacienda.getHaciendaLoteCultivoByIdLotes(Hacienda.getHaciendaById(obj.hacienda).getListadoLotes(), obj.lote);
                obj.leyendaCliente = Cliente.getClienteById(obj.cliente).nombre;
//                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.hacienda).nombre;
//                obj.leyendaLote = obj.lote;
//                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;
//                
                obj.leyendaCanton = Canton.getCantonById(obj.canton).getNombre() + " (" + Canton.getCantonById(obj.canton).getLeyendaPais() + ")";
                obj.leyendaMatriz = Matriz.getById(obj.matriz).getNombre();
                obj.leyendaCourier = Courier.getById(obj.courier).getNombre();

            }

        });

        return obj;
    }

    public static MuestraLaboratorio getMuestraLaboratorioById(ObjectId id) {
        MuestraLaboratorio obj = new MuestraLaboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("muestralaboratorio").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.cliente = (ObjectId) document.get("cliente");
//                obj.hacienda = (ObjectId) document.get("hacienda");
//                obj.lote = document.getString("lote");
//                obj.cultivo = (ObjectId) document.get("cultivo");
                obj.idSiembraCultivo = document.getObjectId("idSiembraCultivo");
                obj.siembraCultivo = SiembraCultivo.getById(obj.idSiembraCultivo);
                obj.codigo = document.get("codigo").toString();
                obj.personaContacto = document.get("personacontacto").toString();
                obj.emailInforme = document.get("emailinforme").toString();
                obj.direccionInforme = document.get("direccioninforme").toString();
                obj.canton = (ObjectId) document.get("canton");
                obj.productor = document.get("productor").toString();
                obj.muestreador = document.get("muestreador").toString();
                obj.observaciones = document.get("observaciones").toString();
                obj.fechaMuestreo = (Date) document.getDate("fechamuestreo");
                obj.fechaEnvio = (Date) document.getDate("fechaenvio");

                obj.matriz = document.getObjectId("matriz");
                obj.courier = document.getObjectId("courier");
                obj.trackerNumber = document.getString("trackerNumber");
                obj.tipoMuestra = document.getString("tipoMuestra");
                obj.fechaCreacion = document.getDate("fechaCreacion");
                obj.departamento = document.getObjectId("departamento");
                obj.leyendaDepartamento = Departamento.getById(obj.departamento).getNombre();
                obj.estacionMonitoreo = document.getString("estacionMonitoreo");
                obj.numeroMonitoreo = document.getInteger("numeroMonitoreo");
                obj.numeroCodigo = document.getInteger("numeroCodigo");
                obj.estadoMuestra = document.getString("estadoMuestra");

                if (obj.fechaCreacion != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatCreacion = formateadorRec.format(obj.fechaCreacion);
                } else {
                    obj.fechaFormatCreacion = "---";
                }

                if (obj.fechaMuestreo != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatMuestreo = formateadorRec.format(obj.fechaMuestreo);
                } else {
                    obj.fechaFormatMuestreo = "---";
                }

                if (obj.fechaEnvio != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatEnvio = formateadorRec.format(obj.fechaEnvio);
                } else {
                    obj.fechaFormatEnvio = "---";
                }

                List<Document> paquetes = (List<Document>) document.get("paquetes");
                List<Document> subana = (List<Document>) document.get("subanalisis");

                for (int i = 0; i < paquetes.size(); i++) {
                    Document dbo = (Document) paquetes.get(i);

                    AnalisisLaboratorio al = AnalisisLaboratorio.getAnalisisLaboratorioById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoPaquetesoAux.add(al);

                }

                for (int i = 0; i < subana.size(); i++) {
                    Document dbo = (Document) subana.get(i);

                    Subanalisis al = Subanalisis.getById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoSubanalisisAux.add(al);

                }

//                obj.loteCompleto = Hacienda.getHaciendaLoteCultivoByIdLotes(Hacienda.getHaciendaById(obj.hacienda).getListadoLotes(), obj.lote);
               obj.leyendaCliente = Cliente.getClienteById(obj.cliente).nombre;
//                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.hacienda).nombre;
//                obj.leyendaLote = obj.lote;
//                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;
                obj.leyendaCanton = Canton.getCantonById(obj.canton).getNombre() + " (" + Canton.getCantonById(obj.canton).getLeyendaPais() + ")";
                obj.leyendaMatriz = Matriz.getById(obj.matriz).getNombre();
                obj.leyendaCourier = Courier.getById(obj.courier).getNombre();
            }

        });

        return obj;
    }

    public static int getNumberMuestraLaboratorio1(ObjectId idmuestra, ObjectId idCliente, ObjectId idHacienda, String idLote, ObjectId idCultivo) {
        int num = 1;

        List<MuestraLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("muestralaboratorio").find(new Document("cliente", idCliente).append("hacienda", idHacienda).append("lote", idLote).append("cultivo", idCultivo)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                MuestraLaboratorio obj = new MuestraLaboratorio();
                obj.id = (ObjectId) document.get("_id");

                res.add(obj);
            }
        });

        int cont = 0;
        while (cont < res.size()) {
            if (res.get(cont).id.equals(idmuestra)) {
                num = cont + 1;
                cont = res.size();
            }

            cont++;
        }

        return num;
    }
    
    public static int getNumberMuestraLaboratorio(ObjectId idmuestra, ObjectId idSiembraCultivo) {
        int num = 1;

        List<MuestraLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("muestralaboratorio").find(new Document("idSiembraCultivo", idSiembraCultivo)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                MuestraLaboratorio obj = new MuestraLaboratorio();
                obj.id = (ObjectId) document.get("_id");

                res.add(obj);
            }
        });

        int cont = 0;
        while (cont < res.size()) {
            if (res.get(cont).id.equals(idmuestra)) {
                num = cont + 1;
                cont = res.size();
            }

            cont++;
        }

        return num;
    }

    public static int getMaxNumeroCodigo() {
        int num = 1;
        List<MuestraLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("muestralaboratorio").find().sort(new Document("numeroCodigo", -1)).limit(1);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                MuestraLaboratorio obj = new MuestraLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.numeroCodigo = document.getInteger("numeroCodigo");
                res.add(obj);
            }
        });

        for (int i = 0; i < res.size(); i++) {
            num = res.get(i).getNumeroCodigo() + 1;
        }
        return num;

    }

    public static List<MuestraLaboratorio> getAllMuestraLaboratorio() {
        List<MuestraLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("muestralaboratorio").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                MuestraLaboratorio obj = new MuestraLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.cliente = (ObjectId) document.get("cliente");
//                obj.hacienda = (ObjectId) document.get("hacienda");
//                obj.lote = document.getString("lote");
//                obj.cultivo = (ObjectId) document.get("cultivo");
                obj.idSiembraCultivo = document.getObjectId("idSiembraCultivo");
                obj.siembraCultivo = SiembraCultivo.getById(obj.idSiembraCultivo);
                obj.codigo = document.get("codigo").toString();
                obj.personaContacto = document.get("personacontacto").toString();
                obj.emailInforme = document.get("emailinforme").toString();
                obj.direccionInforme = document.get("direccioninforme").toString();
                obj.canton = (ObjectId) document.get("canton");
                obj.productor = document.get("productor").toString();
                obj.muestreador = document.get("muestreador").toString();
                obj.observaciones = document.get("observaciones").toString();
                obj.fechaMuestreo = (Date) document.getDate("fechamuestreo");
                obj.fechaEnvio = (Date) document.getDate("fechaenvio");

                obj.matriz = document.getObjectId("matriz");
                obj.courier = document.getObjectId("courier");
                obj.trackerNumber = document.getString("trackerNumber");
                obj.tipoMuestra = document.getString("tipoMuestra");
                obj.fechaCreacion = document.getDate("fechaCreacion");
                obj.departamento = document.getObjectId("departamento");
                obj.leyendaDepartamento = Departamento.getById(obj.departamento).getNombre();
                obj.estacionMonitoreo = document.getString("estacionMonitoreo");
                obj.numeroMonitoreo = document.getInteger("numeroMonitoreo");
                obj.numeroCodigo = document.getInteger("numeroCodigo");
                obj.estadoMuestra = document.getString("estadoMuestra");

                if (obj.fechaCreacion != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatCreacion = formateadorRec.format(obj.fechaCreacion);
                } else {
                    obj.fechaFormatCreacion = "---";
                }

                if (obj.fechaMuestreo != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatMuestreo = formateadorRec.format(obj.fechaMuestreo);
                } else {
                    obj.fechaFormatMuestreo = "---";
                }

                if (obj.fechaEnvio != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatEnvio = formateadorRec.format(obj.fechaEnvio);
                } else {
                    obj.fechaFormatEnvio = "---";
                }

                List<Document> paquetes = (List<Document>) document.get("paquetes");
                List<Document> subana = (List<Document>) document.get("subanalisis");

                for (int i = 0; i < paquetes.size(); i++) {
                    Document dbo = (Document) paquetes.get(i);

                    AnalisisLaboratorio al = AnalisisLaboratorio.getAnalisisLaboratorioById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoPaquetesoAux.add(al);

                }

                for (int i = 0; i < subana.size(); i++) {
                    Document dbo = (Document) subana.get(i);

                    Subanalisis al = Subanalisis.getById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoSubanalisisAux.add(al);

                }

//                obj.loteCompleto = Hacienda.getHaciendaLoteCultivoByIdLotes(Hacienda.getHaciendaById(obj.hacienda).getListadoLotes(), obj.lote);
                obj.leyendaCliente = Cliente.getClienteById(obj.cliente).nombre;
//                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.hacienda).nombre;
//                obj.leyendaLote = obj.lote;
//                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;
                obj.leyendaCanton = Canton.getCantonById(obj.canton).getNombre() + " (" + Canton.getCantonById(obj.canton).getLeyendaPais() + ")";
                obj.leyendaMatriz = Matriz.getById(obj.matriz).getNombre();
                obj.leyendaCourier = Courier.getById(obj.courier).getNombre();

                res.add(obj);
            }

        });

        return res;
    }

    public static List<MuestraLaboratorio> getAllMuestraLaboratorioBetweenDateFechaCreacion(Date fi, Date ff) {
        List<MuestraLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("muestralaboratorio").find(and(gte("fechaCreacion", fi), lte("fechaCreacion", ff)))
                .sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                MuestraLaboratorio obj = new MuestraLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.cliente = (ObjectId) document.get("cliente");
//                obj.hacienda = (ObjectId) document.get("hacienda");
//                obj.lote = document.getString("lote");
//                obj.cultivo = (ObjectId) document.get("cultivo");
                obj.idSiembraCultivo = document.getObjectId("idSiembraCultivo");
                obj.siembraCultivo = SiembraCultivo.getById(obj.idSiembraCultivo);
                obj.codigo = document.get("codigo").toString();
                obj.personaContacto = document.get("personacontacto").toString();
                obj.emailInforme = document.get("emailinforme").toString();
                obj.direccionInforme = document.get("direccioninforme").toString();
                obj.canton = (ObjectId) document.get("canton");
                obj.productor = document.get("productor").toString();
                obj.muestreador = document.get("muestreador").toString();
                obj.observaciones = document.get("observaciones").toString();
                obj.fechaMuestreo = (Date) document.getDate("fechamuestreo");
                obj.fechaEnvio = (Date) document.getDate("fechaenvio");

                obj.matriz = document.getObjectId("matriz");
                obj.courier = document.getObjectId("courier");
                obj.trackerNumber = document.getString("trackerNumber");
                obj.tipoMuestra = document.getString("tipoMuestra");
                obj.fechaCreacion = document.getDate("fechaCreacion");
                obj.departamento = document.getObjectId("departamento");
                obj.leyendaDepartamento = Departamento.getById(obj.departamento).getNombre();
                obj.estacionMonitoreo = document.getString("estacionMonitoreo");
                obj.numeroMonitoreo = document.getInteger("numeroMonitoreo");
                obj.numeroCodigo = document.getInteger("numeroCodigo");
                obj.estadoMuestra = document.getString("estadoMuestra");

                if (obj.fechaCreacion != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatCreacion = formateadorRec.format(obj.fechaCreacion);
                } else {
                    obj.fechaFormatCreacion = "---";
                }

                if (obj.fechaMuestreo != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatMuestreo = formateadorRec.format(obj.fechaMuestreo);
                } else {
                    obj.fechaFormatMuestreo = "---";
                }

                if (obj.fechaEnvio != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatEnvio = formateadorRec.format(obj.fechaEnvio);
                } else {
                    obj.fechaFormatEnvio = "---";
                }

                List<Document> paquetes = (List<Document>) document.get("paquetes");
                List<Document> subana = (List<Document>) document.get("subanalisis");

                for (int i = 0; i < paquetes.size(); i++) {
                    Document dbo = (Document) paquetes.get(i);

                    AnalisisLaboratorio al = AnalisisLaboratorio.getAnalisisLaboratorioById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoPaquetesoAux.add(al);

                }

                for (int i = 0; i < subana.size(); i++) {
                    Document dbo = (Document) subana.get(i);

                    Subanalisis al = Subanalisis.getById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoSubanalisisAux.add(al);

                }

//                obj.loteCompleto = Hacienda.getHaciendaLoteCultivoByIdLotes(Hacienda.getHaciendaById(obj.hacienda).getListadoLotes(), obj.lote);
                obj.leyendaCliente = Cliente.getClienteById(obj.cliente).nombre;
//                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.hacienda).nombre;
//                obj.leyendaLote = obj.lote;
//                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;
                obj.leyendaCanton = Canton.getCantonById(obj.canton).getNombre() + " (" + Canton.getCantonById(obj.canton).getLeyendaPais() + ")";
                obj.leyendaMatriz = Matriz.getById(obj.matriz).getNombre();
                obj.leyendaCourier = Courier.getById(obj.courier).getNombre();

                res.add(obj);
            }

        });

        return res;
    }

    public static List<MuestraLaboratorio> getAllMuestraLaboratorioPlantechSortByEstacionMonitoreoByFechas(Date fi, Date ff) {
        List<MuestraLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("muestralaboratorio").find(and(gte("fechaCreacion", fi), lte("fechaCreacion", ff), eq("tipoMuestra", "PLANTECH")))
                .sort(ascending("cliente", "hacienda", "lote", "estacionMonitoreo"));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                MuestraLaboratorio obj = new MuestraLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.cliente = (ObjectId) document.get("cliente");
//                obj.hacienda = (ObjectId) document.get("hacienda");
//                obj.lote = document.getString("lote");
//                obj.cultivo = (ObjectId) document.get("cultivo");
                obj.idSiembraCultivo = document.getObjectId("idSiembraCultivo");
                obj.siembraCultivo = SiembraCultivo.getById(obj.idSiembraCultivo);
                obj.codigo = document.get("codigo").toString();
                obj.personaContacto = document.get("personacontacto").toString();
                obj.emailInforme = document.get("emailinforme").toString();
                obj.direccionInforme = document.get("direccioninforme").toString();
                obj.canton = (ObjectId) document.get("canton");
                obj.productor = document.get("productor").toString();
                obj.muestreador = document.get("muestreador").toString();
                obj.observaciones = document.get("observaciones").toString();
                obj.fechaMuestreo = (Date) document.getDate("fechamuestreo");
                obj.fechaEnvio = (Date) document.getDate("fechaenvio");

                obj.matriz = document.getObjectId("matriz");
                obj.courier = document.getObjectId("courier");
                obj.trackerNumber = document.getString("trackerNumber");
                obj.tipoMuestra = document.getString("tipoMuestra");
                obj.fechaCreacion = document.getDate("fechaCreacion");
                obj.departamento = document.getObjectId("departamento");
                obj.leyendaDepartamento = Departamento.getById(obj.departamento).getNombre();
                obj.estacionMonitoreo = document.getString("estacionMonitoreo");
                obj.numeroMonitoreo = document.getInteger("numeroMonitoreo");
                obj.numeroCodigo = document.getInteger("numeroCodigo");
                obj.estadoMuestra = document.getString("estadoMuestra");

                if (obj.fechaCreacion != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatCreacion = formateadorRec.format(obj.fechaCreacion);
                } else {
                    obj.fechaFormatCreacion = "---";
                }

                if (obj.fechaMuestreo != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatMuestreo = formateadorRec.format(obj.fechaMuestreo);
                } else {
                    obj.fechaFormatMuestreo = "---";
                }

                if (obj.fechaEnvio != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatEnvio = formateadorRec.format(obj.fechaEnvio);
                } else {
                    obj.fechaFormatEnvio = "---";
                }

                List<Document> paquetes = (List<Document>) document.get("paquetes");
                List<Document> subana = (List<Document>) document.get("subanalisis");

                for (int i = 0; i < paquetes.size(); i++) {
                    Document dbo = (Document) paquetes.get(i);

                    AnalisisLaboratorio al = AnalisisLaboratorio.getAnalisisLaboratorioById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoPaquetesoAux.add(al);

                }

                for (int i = 0; i < subana.size(); i++) {
                    Document dbo = (Document) subana.get(i);

                    Subanalisis al = Subanalisis.getById(dbo.getObjectId("idanalisis"));
                    al.laboratorio = dbo.getObjectId("idanalisis");

                    obj.listadoSubanalisisAux.add(al);

                }

//                obj.loteCompleto = Hacienda.getHaciendaLoteCultivoByIdLotes(Hacienda.getHaciendaById(obj.hacienda).getListadoLotes(), obj.lote);
                obj.leyendaCliente = Cliente.getClienteById(obj.cliente).nombre;
//                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.hacienda).nombre;
//                obj.leyendaLote = obj.lote;
//                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;
                obj.leyendaCanton = Canton.getCantonById(obj.canton).getNombre() + " (" + Canton.getCantonById(obj.canton).getLeyendaPais() + ")";
                obj.leyendaMatriz = Matriz.getById(obj.matriz).getNombre();
                obj.leyendaCourier = Courier.getById(obj.courier).getNombre();

                res.add(obj);
            }

        });

        return res;
    }

    List<AnalisisLaboratorio> loadListAnalisis(List<Document> analisisLista) {
        List<AnalisisLaboratorio> listaux = new ArrayList<>();

        for (int k = 0; k < analisisLista.size(); k++) {
            Document doc = (Document) analisisLista.get(k);
            AnalisisLaboratorio aux = new AnalisisLaboratorio();
            aux = AnalisisLaboratorio.getAnalisisLaboratorioById((ObjectId) doc.getObjectId("idanalisis"));

            listaux.add(aux);
        }

        return listaux;
    }

    List<AnalisisLaboratorioAux> loadListAnalisisAux(List<Document> analisisLista) {
        List<AnalisisLaboratorioAux> listaux = new ArrayList<>();

        for (int k = 0; k < analisisLista.size(); k++) {
            Document doc = (Document) analisisLista.get(k);
            AnalisisLaboratorioAux aux = new AnalisisLaboratorioAux((ObjectId) doc.getObjectId("idanalisis"), (ObjectId) doc.getObjectId("idlaboratorio"));

            listaux.add(aux);
        }

        return listaux;
    }

}
