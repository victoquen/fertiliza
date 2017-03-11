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
 * @author pablo
 */
//PAQUETE ANALISIS
public class AnalisisLaboratorio implements Serializable {

    ObjectId id;
    Integer codigo;
    ObjectId matriz;
    ObjectId laboratorio;
    String descripcion;
    Integer darBaja; //0 no se muestra, 1 se muestra

    List<Subanalisis> listadoElementos;

    String leyendaMatriz;
    String leyendaLaboratorio;
    Integer tat;//no se guarda en BD
    
    BigDecimal costo;

    public AnalisisLaboratorio() {
        this.codigo = 0;
        this.darBaja = 0;
        this.descripcion = "";
        this.tat=0;
        
        listadoElementos = new ArrayList<>();
        this.costo= new BigDecimal(0);
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
    
    public Integer getTat() {
        return tat;
    }

    public void setTat(Integer tat) {
        this.tat = tat;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public ObjectId getMatriz() {
        return matriz;
    }

    public void setMatriz(ObjectId matriz) {
        this.matriz = matriz;
    }

    public ObjectId getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(ObjectId laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Integer getDarBaja() {
        return darBaja;
    }

    public void setDarBaja(Integer darBaja) {
        this.darBaja = darBaja;
    }

    public String getLeyendaMatriz() {
        return leyendaMatriz;
    }

    public void setLeyendaMatriz(String leyendaMatriz) {
        this.leyendaMatriz = leyendaMatriz;
    }

    public String getLeyendaLaboratorio() {
        return leyendaLaboratorio;
    }

    public void setLeyendaLaboratorio(String leyendaLaboratorio) {
        this.leyendaLaboratorio = leyendaLaboratorio;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Subanalisis> getListadoElementos() {
        return listadoElementos;
    }

    public void setListadoElementos(List<Subanalisis> listadoElementos) {
        this.listadoElementos = listadoElementos;
    }

    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        List<Document> elementos = new ArrayList<>();
        for (int i = 0; i < listadoElementos.size(); i++) {
            Document aux = new Document().append("elemento", listadoElementos.get(i).id);
            elementos.add(aux);
        }

        MongoCollection table = mongo.db.getCollection("analisislaboratorio");
        Document obj = new Document("codigo", this.codigo).append("matriz", this.matriz)
                .append("laboratorio", this.laboratorio).append("descripcion", this.descripcion)
                .append("costo", BDecimalToStr(this.costo)).append("darBaja", this.darBaja)
                .append("elementos", elementos);
        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        //AnalisisLaboratorio before = getAnalisisLaboratorioById(this.id);
        List<Document> elementos = new ArrayList<>();
        for (int i = 0; i < listadoElementos.size(); i++) {
            Document aux = new Document().append("elemento", listadoElementos.get(i).id);
            elementos.add(aux);
        }

        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("analisislaboratorio").updateOne(new Document("_id", this.id), new Document("$set", new Document("codigo", this.codigo).append("matriz", this.matriz)
                .append("laboratorio", this.laboratorio).append("descripcion", this.descripcion)
                .append("costo", BDecimalToStr(this.costo)).append("darBaja", this.darBaja)
                .append("elementos", elementos)));

    }

    public static AnalisisLaboratorio getAnalisisLaboratorioById(ObjectId id) {
        AnalisisLaboratorio obj = new AnalisisLaboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("analisislaboratorio").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.matriz = (ObjectId) document.get("matriz");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.descripcion = document.get("descripcion").toString();
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.darBaja = document.getInteger("darBaja");

                List<Document> lista = (List<Document>) document.get("elementos");
                for (int i = 0; i < lista.size(); i++) {
                    Document dbo = (Document) lista.get(i);
                    Subanalisis aux = new Subanalisis();
                    aux = Subanalisis.getById((ObjectId) dbo.get("elemento"));
                    if(aux.getTat()>obj.tat){
                        obj.tat = aux.getTat();
                    }
                    obj.listadoElementos.add(aux);
                }

                obj.leyendaMatriz = Matriz.getById(obj.matriz).nombre;
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.laboratorio).nombre  + " [" + Laboratorio.getLaboratorioById(obj.laboratorio).getPais() + "]";
            }

        });

        return obj;
    }

    public static List<AnalisisLaboratorio> getAllAnalisis() {
        List<AnalisisLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("analisislaboratorio").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                AnalisisLaboratorio obj = new AnalisisLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.matriz = (ObjectId) document.get("matriz");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.descripcion = document.get("descripcion").toString();
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.darBaja = document.getInteger("darBaja");

                List<Document> lista = (List<Document>) document.get("elementos");
                for (int i = 0; i < lista.size(); i++) {
                    Document dbo = (Document) lista.get(i);
                    Subanalisis aux = new Subanalisis();
                    aux = Subanalisis.getById((ObjectId) dbo.get("elemento"));
                    if(aux.getTat()>obj.tat){
                        obj.tat = aux.getTat();
                    }                    
                    obj.listadoElementos.add(aux);
                }

                obj.leyendaMatriz = Matriz.getById(obj.matriz).nombre;
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.laboratorio).nombre  + " [" + Laboratorio.getLaboratorioById(obj.laboratorio).getPais() + "]";

                res.add(obj);
            }

        });

        return res;
    }
    
    public static List<AnalisisLaboratorio> getAllAnalisisActivo() {
        List<AnalisisLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("analisislaboratorio").find(new Document("darBaja", 0)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                AnalisisLaboratorio obj = new AnalisisLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.matriz = (ObjectId) document.get("matriz");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.descripcion = document.get("descripcion").toString();
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.darBaja = document.getInteger("darBaja");

                List<Document> lista = (List<Document>) document.get("elementos");
                for (int i = 0; i < lista.size(); i++) {
                    Document dbo = (Document) lista.get(i);
                    Subanalisis aux = new Subanalisis();
                    aux = Subanalisis.getById((ObjectId) dbo.get("elemento"));
                    if(aux.getTat()>obj.tat){
                        obj.tat = aux.getTat();
                    }
                    obj.listadoElementos.add(aux);
                }

                obj.leyendaMatriz = Matriz.getById(obj.matriz).nombre;
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.laboratorio).nombre + " [" + Laboratorio.getLaboratorioById(obj.laboratorio).pais + "]";

                res.add(obj);
            }

        });

        return res;
    }
    
    public static List<AnalisisLaboratorio> getAllAnalisisActivoByMatriz(ObjectId matr) {
        List<AnalisisLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("analisislaboratorio").find(new Document("darBaja", 0).append("matriz", matr)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                AnalisisLaboratorio obj = new AnalisisLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.matriz = (ObjectId) document.get("matriz");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.descripcion = document.get("descripcion").toString();
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.darBaja = document.getInteger("darBaja");

                List<Document> lista = (List<Document>) document.get("elementos");
                for (int i = 0; i < lista.size(); i++) {
                    Document dbo = (Document) lista.get(i);
                    Subanalisis aux = new Subanalisis();
                    aux = Subanalisis.getById((ObjectId) dbo.get("elemento"));
                    if(aux.getTat()>obj.tat){
                        obj.tat = aux.getTat();
                    }
                    obj.listadoElementos.add(aux);
                }

                obj.leyendaMatriz = Matriz.getById(obj.matriz).nombre;
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.laboratorio).nombre + " [" + Laboratorio.getLaboratorioById(obj.laboratorio).pais + "]";

                res.add(obj);
            }

        });

        return res;
    }
    
    public static List<AnalisisLaboratorio> getAllAnalisisDadoBaja() {
        List<AnalisisLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("analisislaboratorio").find(new Document("darBaja", 1)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                AnalisisLaboratorio obj = new AnalisisLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.matriz = (ObjectId) document.get("matriz");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.descripcion = document.get("descripcion").toString();
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.darBaja = document.getInteger("darBaja");

                List<Document> lista = (List<Document>) document.get("elementos");
                for (int i = 0; i < lista.size(); i++) {
                    Document dbo = (Document) lista.get(i);
                    Subanalisis aux = new Subanalisis();
                    aux = Subanalisis.getById((ObjectId) dbo.get("elemento"));
                    if(aux.getTat()>obj.tat){
                        obj.tat = aux.getTat();
                    }
                    obj.listadoElementos.add(aux);
                }

                obj.leyendaMatriz = Matriz.getById(obj.matriz).nombre;
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.laboratorio).nombre   + " [" + Laboratorio.getLaboratorioById(obj.laboratorio).getPais() + "]";

                res.add(obj);
            }

        });

        return res;
    }

    public static List<AnalisisLaboratorio> getAllAnalisisLaboratorioByTipo(String op) {
        List<AnalisisLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("analisislaboratorio").find(new Document("tipomatriz", op)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                AnalisisLaboratorio obj = new AnalisisLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.matriz = (ObjectId) document.get("matriz");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.descripcion = document.get("descripcion").toString();
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.darBaja = document.getInteger("darBaja");

                List<Document> lista = (List<Document>) document.get("elementos");
                for (int i = 0; i < lista.size(); i++) {
                    Document dbo = (Document) lista.get(i);
                    Subanalisis aux = new Subanalisis();
                    aux = Subanalisis.getById((ObjectId) dbo.get("elemento"));
                    if(aux.getTat()>obj.tat){
                        obj.tat = aux.getTat();
                    }
                    obj.listadoElementos.add(aux);
                }

                obj.leyendaMatriz = Matriz.getById(obj.matriz).nombre;
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.laboratorio).nombre  + " [" + Laboratorio.getLaboratorioById(obj.laboratorio).getPais() + "]";

                res.add(obj);
            }

        });

        return res;
    }

    public static Integer getMaxNumeroSecuencialCodigo() {

        Integer res = 0;
        AnalisisLaboratorio obj = new AnalisisLaboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("analisislaboratorio").find().sort(new Document("codigo", -1)).limit(1);

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.codigo = document.getInteger("codigo");
            }
        });

        if (obj.codigo != null) {
            res = obj.codigo;
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
