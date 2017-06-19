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
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class Produccion implements Serializable {

    ObjectId id;
    Integer anio;
    String ciclo;
    ObjectId idCliente;
    ObjectId idHacienda;
    ObjectId idCultivo;
    ObjectId idVariedad;
    String unidadProduccion;
    BigDecimal factorConversion;
    BigDecimal totalProduccion;
    BigDecimal totalKgAnual;
    BigDecimal mermaNatural; //12%
    BigDecimal mermaFitosanitaria; //10%
    BigDecimal proyeccion; //2%

    List<ProduccionLote> listadoProduccionLote;

    String leyendaCliente;
    String leyendaHacienda;
    String leyendaCultivo;
    String leyendaVariedad;
    String leyendaVariacion;
    String leyendaProduccionBruta;

    public Produccion() {
        anio = 0;
        ciclo = "";
        unidadProduccion = "";
        factorConversion = new BigDecimal(0);
        totalProduccion = new BigDecimal(0);
        totalKgAnual = new BigDecimal(0);
        mermaNatural = new BigDecimal(0);
        mermaFitosanitaria = new BigDecimal(0);
        proyeccion = new BigDecimal(0);
        listadoProduccionLote = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public ObjectId getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ObjectId idCliente) {
        this.idCliente = idCliente;
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

    public String getUnidadProduccion() {
        return unidadProduccion;
    }

    public void setUnidadProduccion(String unidadProduccion) {
        this.unidadProduccion = unidadProduccion;
    }

    public BigDecimal getFactorConversion() {
        return factorConversion;
    }

    public void setFactorConversion(BigDecimal factorConversion) {
        this.factorConversion = factorConversion;
    }

    public BigDecimal getTotalProduccion() {
        return totalProduccion;
    }

    public void setTotalProduccion(BigDecimal totalProduccion) {
        this.totalProduccion = totalProduccion;
    }

    public BigDecimal getTotalKgAnual() {
        return totalKgAnual;
    }

    public void setTotalKgAnual(BigDecimal totalKgAnual) {
        this.totalKgAnual = totalKgAnual;
    }

    public BigDecimal getMermaNatural() {
        return mermaNatural;
    }

    public void setMermaNatural(BigDecimal mermaNatural) {
        this.mermaNatural = mermaNatural;
    }

    public BigDecimal getMermaFitosanitaria() {
        return mermaFitosanitaria;
    }

    public void setMermaFitosanitaria(BigDecimal mermaFitosanitaria) {
        this.mermaFitosanitaria = mermaFitosanitaria;
    }

    public BigDecimal getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(BigDecimal proyeccion) {
        this.proyeccion = proyeccion;
    }

    public List<ProduccionLote> getListadoProduccionLote() {
        return listadoProduccionLote;
    }

    public void setListadoProduccionLote(List<ProduccionLote> listadoProduccionLote) {
        this.listadoProduccionLote = listadoProduccionLote;
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

    public String getLeyendaVariacion() {
        return leyendaVariacion;
    }

    public void setLeyendaVariacion(String leyendaVariacion) {
        this.leyendaVariacion = leyendaVariacion;
    }

    public String getLeyendaProduccionBruta() {
        return leyendaProduccionBruta;
    }

    public void setLeyendaProduccionBruta(String leyendaProduccionBruta) {
        this.leyendaProduccionBruta = leyendaProduccionBruta;
    }

    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        List<Document> lista = new ArrayList<>();
        for (int i = 0; i < listadoProduccionLote.size(); i++) {
            Document aux = new Document("idLote", listadoProduccionLote.get(i).idLote).append("prodLote", BDecimalToStr(listadoProduccionLote.get(i).prodLote));
            lista.add(aux);
        }

        MongoCollection table = mongo.db.getCollection("produccion");
        Document obj = new Document("anio", this.anio)
                .append("ciclo", this.ciclo)
                .append("idCliente", this.idCliente)
                .append("idHacienda", this.idHacienda)
                .append("idCultivo", this.idCultivo)
                .append("idVariedad", this.idVariedad)
                .append("unidadProduccion", this.unidadProduccion)
                .append("factorConversion", BDecimalToStr(this.factorConversion))
                .append("totalProduccion", BDecimalToStr(this.totalProduccion))
                .append("totalKgAnual", BDecimalToStr(this.totalKgAnual))
                .append("mermaNatural", BDecimalToStr(this.mermaNatural))
                .append("mermaFitosanitaria", BDecimalToStr(this.mermaFitosanitaria))
                .append("proyeccion", BDecimalToStr(this.proyeccion))
                .append("produccionLote", lista);
        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        List<Document> lista = new ArrayList<>();
        for (int i = 0; i < listadoProduccionLote.size(); i++) {
            Document aux = new Document("idLote", listadoProduccionLote.get(i).idLote).append("prodLote", BDecimalToStr(listadoProduccionLote.get(i).prodLote));
            lista.add(aux);
        }

        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("produccion").updateOne(new Document("_id", this.id),
                new Document("$set", new Document("anio", this.anio)
                        .append("ciclo", this.ciclo)
                        .append("idCliente", this.idCliente)
                        .append("idHacienda", this.idHacienda)
                        .append("idCultivo", this.idCultivo)
                        .append("idVariedad", this.idVariedad)
                        .append("unidadProduccion", this.unidadProduccion)
                        .append("factorConversion", BDecimalToStr(this.factorConversion))
                        .append("totalProduccion", BDecimalToStr(this.totalProduccion))
                        .append("totalKgAnual", BDecimalToStr(this.totalKgAnual))
                        .append("mermaNatural", BDecimalToStr(this.mermaNatural))
                        .append("mermaFitosanitaria", BDecimalToStr(this.mermaFitosanitaria))
                        .append("proyeccion", BDecimalToStr(this.proyeccion))
                        .append("produccionLote", lista)
                ));

    }

    public static Produccion getById(ObjectId id) {
        Produccion obj = new Produccion();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("produccion").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = document.getObjectId("_id");
                obj.anio = document.getInteger("anio");
                obj.ciclo = document.getString("ciclo");
                obj.idCliente = document.getObjectId("idCliente");
                obj.idHacienda = document.getObjectId("idHacienda");
                obj.idCultivo = document.getObjectId("idCultivo");
                obj.idVariedad = document.getObjectId("idVariedad");
                obj.unidadProduccion = document.getString("unidadProduccion");
                obj.factorConversion = obj.StrToBDecimal(document.getString("factorConversion"));
                obj.totalProduccion = obj.StrToBDecimal(document.getString("totalProduccion"));
                obj.totalKgAnual = obj.StrToBDecimal(document.getString("totalKgAnual"));
                obj.mermaNatural = obj.StrToBDecimal(document.getString("mermaNatural"));
                obj.mermaFitosanitaria = obj.StrToBDecimal(document.getString("mermaFitosanitaria"));
                obj.proyeccion = obj.StrToBDecimal(document.getString("proyeccion"));

                obj.leyendaCliente = Cliente.getClienteById(obj.idCliente).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).nombre;
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).nombre;

                List<Document> lista = (List<Document>) document.get("produccionLote");
                for (int i = 0; i < lista.size(); i++) {
                    Document dbo = (Document) lista.get(i);
                    ProduccionLote aux = new ProduccionLote();
                    aux.idLote = (ObjectId) dbo.get("idLote");
                    aux.prodLote = obj.StrToBDecimal(dbo.getString("prodLote"));
                    aux.leyendaLote = Lote.getLoteById(aux.idLote).codigo;
                    aux.leyendaHectareas = Lote.getLoteById(aux.idLote).hectareas;
                    obj.listadoProduccionLote.add(aux);
                }

            }
        });

        return obj;
    }

    public static List<Produccion> getAll() {
        List<Produccion> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("produccion").find().sort(new Document("anio", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Produccion obj = new Produccion();
                obj.id = document.getObjectId("_id");
                obj.anio = document.getInteger("anio");
                obj.ciclo = document.getString("ciclo");
                obj.idCliente = document.getObjectId("idCliente");
                obj.idHacienda = document.getObjectId("idHacienda");
                obj.idCultivo = document.getObjectId("idCultivo");
                obj.idVariedad = document.getObjectId("idVariedad");
                obj.unidadProduccion = document.getString("unidadProduccion");
                obj.factorConversion = obj.StrToBDecimal(document.getString("factorConversion"));
                obj.totalProduccion = obj.StrToBDecimal(document.getString("totalProduccion"));
                obj.totalKgAnual = obj.StrToBDecimal(document.getString("totalKgAnual"));
                obj.mermaNatural = obj.StrToBDecimal(document.getString("mermaNatural"));
                obj.mermaFitosanitaria = obj.StrToBDecimal(document.getString("mermaFitosanitaria"));
                obj.proyeccion = obj.StrToBDecimal(document.getString("proyeccion"));

                obj.leyendaCliente = Cliente.getClienteById(obj.idCliente).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).nombre;
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).nombre;

                List<Document> lista = (List<Document>) document.get("produccionLote");
                int n = lista.size();
                for (int i = 0; i < n; i++) {
                    Document dbo = (Document) lista.get(i);
                    ProduccionLote aux = new ProduccionLote();
                    aux.idLote = (ObjectId) dbo.get("idLote");
                    aux.prodLote = obj.StrToBDecimal(dbo.getString("prodLote"));
                    aux.leyendaLote = Lote.getLoteById(aux.idLote).codigo;
                    aux.leyendaHectareas = Lote.getLoteById(aux.idLote).hectareas;
                    obj.listadoProduccionLote.add(aux);
                }

                List<Produccion> listVariacion = Produccion.getAllByClienteHacCultVar(obj.idCliente, obj.idHacienda, obj.idCultivo, obj.idVariedad);
                if (listVariacion.size() > 0) {
                    int cont = 0;
                    int tot = listVariacion.size();
                    while (cont < tot) {
                        if (listVariacion.get(cont).getTotalKgAnual().compareTo(obj.totalKgAnual) == 0) {
                            if (cont < (tot - 1)) {
                                obj.leyendaVariacion = (obj.totalKgAnual.subtract(listVariacion.get(cont + 1).getTotalKgAnual())).toString();

                            } else {
                                obj.leyendaVariacion = "0";
                            }
                            cont = tot;
                        }

                        cont++;
                    }

                } else {
                    obj.leyendaVariacion = "0";
                }

                obj.leyendaProduccionBruta = (obj.totalKgAnual.subtract(obj.mermaNatural.add(obj.mermaFitosanitaria))).toString();
                res.add(obj);

            }

        });

        return res;
    }

    public static List<Produccion> getAllByClienteHacCultVar(ObjectId idCli, ObjectId idHac, ObjectId idCult, ObjectId idVar) {
        List<Produccion> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("produccion").find(new Document("idCliente", idCli).append("idHacienda", idHac).append("idCultivo", idCult).append("idVariedad", idVar)).sort(new Document("anio", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Produccion obj = new Produccion();
                obj.id = document.getObjectId("_id");
                obj.anio = document.getInteger("anio");

                obj.totalProduccion = obj.StrToBDecimal(document.getString("totalProduccion"));
                obj.totalKgAnual = obj.StrToBDecimal(document.getString("totalKgAnual"));

                res.add(obj);
            }

        });

        return res;
    }
    
    public static List<Produccion> getAllByClienteHacienda(ObjectId idCli, ObjectId idHac) {
        List<Produccion> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("produccion").find(new Document("idCliente", idCli).append("idHacienda", idHac)).sort(new Document("anio", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Produccion obj = new Produccion();
                obj.id = document.getObjectId("_id");
                obj.anio = document.getInteger("anio");
                obj.ciclo = document.getString("ciclo");
                obj.idCliente = document.getObjectId("idCliente");
                obj.idHacienda = document.getObjectId("idHacienda");
                obj.idCultivo = document.getObjectId("idCultivo");
                obj.idVariedad = document.getObjectId("idVariedad");
                obj.unidadProduccion = document.getString("unidadProduccion");
                obj.factorConversion = obj.StrToBDecimal(document.getString("factorConversion"));
                obj.totalProduccion = obj.StrToBDecimal(document.getString("totalProduccion"));
                obj.totalKgAnual = obj.StrToBDecimal(document.getString("totalKgAnual"));
                obj.mermaNatural = obj.StrToBDecimal(document.getString("mermaNatural"));
                obj.mermaFitosanitaria = obj.StrToBDecimal(document.getString("mermaFitosanitaria"));
                obj.proyeccion = obj.StrToBDecimal(document.getString("proyeccion"));

                obj.leyendaCliente = Cliente.getClienteById(obj.idCliente).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).nombre;
                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).nombre;

                List<Document> lista = (List<Document>) document.get("produccionLote");
                int n = lista.size();
                for (int i = 0; i < n; i++) {
                    Document dbo = (Document) lista.get(i);
                    ProduccionLote aux = new ProduccionLote();
                    aux.idLote = (ObjectId) dbo.get("idLote");
                    aux.prodLote = obj.StrToBDecimal(dbo.getString("prodLote"));
                    aux.leyendaLote = Lote.getLoteById(aux.idLote).codigo;
                    aux.leyendaHectareas = Lote.getLoteById(aux.idLote).hectareas;
                    obj.listadoProduccionLote.add(aux);
                }

                List<Produccion> listVariacion = Produccion.getAllByClienteHacCultVar(obj.idCliente, obj.idHacienda, obj.idCultivo, obj.idVariedad);
                if (listVariacion.size() > 0) {
                    int cont = 0;
                    int tot = listVariacion.size();
                    while (cont < tot) {
                        if (listVariacion.get(cont).getTotalKgAnual().compareTo(obj.totalKgAnual) == 0) {
                            if (cont < (tot - 1)) {
                                obj.leyendaVariacion = (obj.totalKgAnual.subtract(listVariacion.get(cont + 1).getTotalKgAnual())).toString();

                            } else {
                                obj.leyendaVariacion = "0";
                            }
                            cont = tot;
                        }

                        cont++;
                    }

                } else {
                    obj.leyendaVariacion = "0";
                }

                obj.leyendaProduccionBruta = (obj.totalKgAnual.subtract(obj.mermaNatural.add(obj.mermaFitosanitaria))).toString();
                res.add(obj);

            }

        });

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
