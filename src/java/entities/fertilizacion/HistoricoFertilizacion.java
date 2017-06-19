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
public class HistoricoFertilizacion implements Serializable {

    ObjectId id;
    ObjectId idProduccion;
    List<HistoricoFertilizacionLote> listadoHistoricoFertilizacionLote;

    ObjectId idCliente;
    ObjectId idHacienda;
    ObjectId idCultivo;
    ObjectId idVariedad;
    Integer anio;
    Produccion produccion;
    String leyendaCliente;
    String leyendaHacienda;
    String leyendaCultivo;
    String leyendaVariedad;
    
    

    public HistoricoFertilizacion() {
        anio = 0;
        produccion = new Produccion();
        listadoHistoricoFertilizacionLote = new ArrayList<>();
        leyendaCliente = "";
        leyendaHacienda = "";
        leyendaCultivo = "";
        leyendaVariedad = "";
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public List<HistoricoFertilizacionLote> getListadoHistoricoFertilizacionLote() {
        return listadoHistoricoFertilizacionLote;
    }

    public void setListadoHistoricoFertilizacionLote(List<HistoricoFertilizacionLote> listadoHistoricoFertilizacionLote) {
        this.listadoHistoricoFertilizacionLote = listadoHistoricoFertilizacionLote;
    }

    public ObjectId getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(ObjectId idProduccion) {
        this.idProduccion = idProduccion;
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

    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        List<Document> lista = new ArrayList<>();
        for (int i = 0; i < listadoHistoricoFertilizacionLote.size(); i++) {
            Document aux = new Document("idLote", listadoHistoricoFertilizacionLote.get(i).idLote)
                    .append("n", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).n))
                    .append("p", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).p))
                    .append("k", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).k))
                    .append("ca", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).ca))
                    .append("mg", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).mg))
                    .append("s", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).s))
                    .append("b", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).b))
                    .append("mn", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).mn))
                    .append("zn", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).zn))
                    .append("cu", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).cu));
            lista.add(aux);
        }

        MongoCollection table = mongo.db.getCollection("historicoFertilizacion");
        Document obj = new Document("idProduccion", this.idProduccion)
                .append("fertilizacionLote", lista);
        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        List<Document> lista = new ArrayList<>();
        for (int i = 0; i < listadoHistoricoFertilizacionLote.size(); i++) {
            Document aux = new Document("idLote", listadoHistoricoFertilizacionLote.get(i).idLote)
                    .append("n", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).n))
                    .append("p", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).p))
                    .append("k", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).k))
                    .append("ca", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).ca))
                    .append("mg", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).mg))
                    .append("s", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).s))
                    .append("b", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).b))
                    .append("mn", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).mn))
                    .append("zn", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).zn))
                    .append("cu", BDecimalToStr(listadoHistoricoFertilizacionLote.get(i).cu));
            lista.add(aux);
        }

        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("historicoFertilizacion").updateOne(new Document("_id", this.id),
                new Document("$set", new Document("idProduccion", this.idProduccion)
                        .append("fertilizacionLote", lista)
                ));

    }

    public static HistoricoFertilizacion getById(ObjectId id) {
        HistoricoFertilizacion obj = new HistoricoFertilizacion();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("historicoFertilizacion").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = document.getObjectId("_id");
                obj.produccion = Produccion.getById(document.getObjectId("idProduccion"));
                
                obj.idCliente = obj.produccion.getIdCliente();
                obj.idHacienda = obj.produccion.getIdHacienda();
                obj.idProduccion = obj.produccion.getId();

                List<Document> lista = (List<Document>) document.get("fertilizacionLote");
                int n = lista.size();
                for (int i = 0; i < n; i++) {
                    Document dbo = (Document) lista.get(i);
                    HistoricoFertilizacionLote aux = new HistoricoFertilizacionLote();
                    aux.idLote = (ObjectId) dbo.get("idLote");
                    aux.n = obj.StrToBDecimal(dbo.getString("n"));
                    aux.p = obj.StrToBDecimal(dbo.getString("p"));
                    aux.k = obj.StrToBDecimal(dbo.getString("k"));
                    aux.ca = obj.StrToBDecimal(dbo.getString("ca"));
                    aux.mg = obj.StrToBDecimal(dbo.getString("mg"));
                    aux.s = obj.StrToBDecimal(dbo.getString("s"));
                    aux.b = obj.StrToBDecimal(dbo.getString("b"));
                    aux.mn = obj.StrToBDecimal(dbo.getString("mn"));
                    aux.zn = obj.StrToBDecimal(dbo.getString("zn"));
                    aux.cu = obj.StrToBDecimal(dbo.getString("cu"));
                    aux.leyendaLote = Lote.getLoteById(aux.idLote).codigo;
                    aux.leyendaHectareas = Lote.getLoteById(aux.idLote).hectareas;

                    int m = obj.produccion.getListadoProduccionLote().size();
                    for (int j = 0; j < m; j++) {
                        if (aux.idLote.equals(obj.produccion.getListadoProduccionLote().get(j).getIdLote())) {
                            aux.prodLote = obj.produccion.getListadoProduccionLote().get(j).getProdLote();
                        }
                    }
                    obj.listadoHistoricoFertilizacionLote.add(aux);
                }

            }
        });

        return obj;
    }

    public static List<HistoricoFertilizacion> getAll() {
        List<HistoricoFertilizacion> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("historicoFertilizacion").find().sort(new Document("anio", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                HistoricoFertilizacion obj = new HistoricoFertilizacion();

                obj.id = document.getObjectId("_id");
                obj.produccion = Produccion.getById(document.getObjectId("idProduccion"));
                
                obj.idCliente = obj.produccion.getIdCliente();
                obj.idHacienda = obj.produccion.getIdHacienda();
                obj.idProduccion = obj.produccion.getId();

                List<Document> lista = (List<Document>) document.get("fertilizacionLote");
                int n = lista.size();
                for (int i = 0; i < n; i++) {
                    Document dbo = (Document) lista.get(i);
                    HistoricoFertilizacionLote aux = new HistoricoFertilizacionLote();
                    aux.idLote = (ObjectId) dbo.get("idLote");
                    aux.n = obj.StrToBDecimal(dbo.getString("n"));
                    aux.p = obj.StrToBDecimal(dbo.getString("p"));
                    aux.k = obj.StrToBDecimal(dbo.getString("k"));
                    aux.ca = obj.StrToBDecimal(dbo.getString("ca"));
                    aux.mg = obj.StrToBDecimal(dbo.getString("mg"));
                    aux.s = obj.StrToBDecimal(dbo.getString("s"));
                    aux.b = obj.StrToBDecimal(dbo.getString("b"));
                    aux.mn = obj.StrToBDecimal(dbo.getString("mn"));
                    aux.zn = obj.StrToBDecimal(dbo.getString("zn"));
                    aux.cu = obj.StrToBDecimal(dbo.getString("cu"));
                    aux.leyendaLote = Lote.getLoteById(aux.idLote).codigo;
                    aux.leyendaHectareas = Lote.getLoteById(aux.idLote).hectareas;

                    int m = obj.produccion.getListadoProduccionLote().size();
                    for (int j = 0; j < m; j++) {
                        if (aux.idLote.equals(obj.produccion.getListadoProduccionLote().get(j).getIdLote())) {
                            aux.prodLote = obj.produccion.getListadoProduccionLote().get(j).getProdLote();
                        }
                    }
                    obj.listadoHistoricoFertilizacionLote.add(aux);
                }

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
