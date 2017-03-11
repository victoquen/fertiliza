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
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class Lote implements Serializable {

    ObjectId id;
    ObjectId idHacienda;
    ObjectId idCultivo;
    ObjectId idVariedad;
    ObjectId idEdad;

    String codigo;

    String hectareas;
    String tipoSuelo;

    String fecha;

    String unidad;
    List<EstacionMonitoreo> estacion;

    String leyendaHacienda;
    String leyendaCultivo;
    String leyendaVariedad;
    String leyendaEdad;

    public Lote() {
        this.codigo = "";

        this.hectareas = "";
        this.tipoSuelo = "";

        this.unidad = "";
        this.estacion = new ArrayList<>();

        this.fecha = "";

        this.leyendaHacienda = "";
        this.leyendaCultivo = "";
        this.leyendaVariedad = "";
        this.leyendaEdad = "";
    }

    public Lote(String codigo, String hectareas, String tipoSuelo, String fecha) {
        this.codigo = codigo;
        this.hectareas = hectareas;
        this.tipoSuelo = tipoSuelo;

        this.fecha = fecha;
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

    public ObjectId getIdEdad() {
        return idEdad;
    }

    public void setIdEdad(ObjectId idEdad) {
        this.idEdad = idEdad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLeyendaVariedad() {
        return leyendaVariedad;
    }

    public void setLeyendaVariedad(String leyendaVariedad) {
        this.leyendaVariedad = leyendaVariedad;
    }

    public String getLeyendaEdad() {
        return leyendaEdad;
    }

    public void setLeyendaEdad(String leyendaEdad) {
        this.leyendaEdad = leyendaEdad;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdHacienda() {
        return idHacienda;
    }

    public void setIdHacienda(ObjectId idHacienda) {
        this.idHacienda = idHacienda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHectareas() {
        return hectareas;
    }

    public void setHectareas(String hectareas) {
        this.hectareas = hectareas;
    }

    public String getTipoSuelo() {
        return tipoSuelo;
    }

    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public List<EstacionMonitoreo> getEstacion() {
        return estacion;
    }

    public void setEstacion(List<EstacionMonitoreo> estacion) {
        this.estacion = estacion;
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

    public void save() {
        
        
        List<Document> listObj = new ArrayList<>();
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("lote");

        Document obj = new Document("idhacienda", this.idHacienda).append("idcultivo", this.idCultivo)
                .append("idvariedad", this.idVariedad).append("idedad", this.idEdad)
                .append("codigo", this.codigo.toUpperCase()).append("hectareas", this.hectareas)
                .append("tiposuelo", this.tipoSuelo).append("unidad", this.unidad.toUpperCase());

        for (int i = 0; i < estacion.size(); i++) {

            /*List<String> ls = this.estacion.get(i).getSondas();
            StringBuilder auxSondas = new StringBuilder(75);
            for (int j = 0; j < ls.size(); j++) {
                auxSondas.append(ls.get(j)).append(";");
            }*/
            List<Document> listObjSonda = new ArrayList<>();
            List<SondaAux> ls = this.estacion.get(i).getListaSondas();
            for(int j=0; j<ls.size(); j++){
                Document sonda = new Document().append("tiposonda", ls.get(j).tipoSonda).append("descripcion", ls.get(j).descripcion);
                listObjSonda.add(sonda);
            }

            Document est = new Document().append("codigo", this.estacion.get(i).codigo).append("latitudestacion", this.estacion.get(i).latitudestacion)
                    .append("longitudestacion", this.estacion.get(i).longitudestacion).append("sondas", listObjSonda);

            listObj.add(est);

        }
        table.insertOne(obj.append("estacion", asList(listObj)));
    }

    public void update() {

        List<Document> listObj = new ArrayList<>();
        Lote before = getLoteById(this.id);

        MongoManager mongo = MongoManager.getInstance();
        Document obj = new Document("$set", new Document("idhacienda", this.idHacienda).append("idcultivo", this.idCultivo)
                .append("idvariedad", this.idVariedad).append("idedad", this.idEdad)
                .append("codigo", this.codigo.toUpperCase()).append("hectareas", this.hectareas)
                .append("tiposuelo", this.tipoSuelo).append("unidad", this.unidad.toUpperCase()));

        for (int i = 0; i < estacion.size(); i++) {

            /*List<String> ls = this.estacion.get(i).getSondas();
            StringBuilder auxSondas = new StringBuilder(75);
            for (int j = 0; j < ls.size(); j++) {
                auxSondas.append(ls.get(j)).append(";");
            }*/
            List<Document> listObjSonda = new ArrayList<>();
            List<SondaAux> ls = this.estacion.get(i).getListaSondas();
            for(int j=0; j<ls.size(); j++){
                Document sonda = new Document().append("tiposonda", ls.get(j).tipoSonda).append("descripcion", ls.get(j).descripcion);
                listObjSonda.add(sonda);
            }

            Document est = new Document().append("codigo", this.estacion.get(i).codigo).append("latitudestacion", this.estacion.get(i).latitudestacion)
                    .append("longitudestacion", this.estacion.get(i).longitudestacion).append("sondas", listObjSonda);

            listObj.add(est);

        }

        mongo.db.getCollection("lote").updateOne(new Document("_id", this.id), obj.append("estacion", asList(listObj)));

    }

    public static Lote getLoteByCodigo(String cod) {
        Lote obj = new Lote();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("lote").find(new Document("codigo", cod));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.idHacienda = (ObjectId) document.get("idhacienda");
                obj.idCultivo = (ObjectId) document.get("idcultivo");
                obj.idVariedad = (ObjectId) document.get("idvariedad");
                obj.idEdad = (ObjectId) document.get("idedad");
                obj.codigo = document.get("codigo").toString();
                obj.hectareas = document.get("hectareas").toString();
                obj.tipoSuelo = document.get("tiposuelo").toString();
                //obj.fecha = document.get("fecha").toString();
                obj.unidad = document.get("unidad").toString();

                List<Document> ests = (List<Document>) document.get("estacion");
                for (int i = 0; i < ests.size(); i++) {

                    List<Document> res = (List<Document>) ests.get(i);
                    for (int j = 0; j < res.size(); j++) {

                        Document dbo = (Document) res.get(j);
                        EstacionMonitoreo aux = new EstacionMonitoreo();
                        aux.codigo = dbo.get("codigo").toString();
                        aux.latitudestacion = dbo.get("latitudestacion").toString();
                        aux.longitudestacion = dbo.get("longitudestacion").toString();
                       
                        
                        
                        List<Document> sondas = (List<Document>) dbo.get("sondas");                        
                        for(int k=0; k<sondas.size(); k++){                        
                            Document dboSonda = (Document) sondas.get(k);                           
                            SondaAux auxSonda = new SondaAux();                            
                            auxSonda.tipoSonda = (ObjectId) dboSonda.get("tiposonda");
                            auxSonda.descripcion = dboSonda.getString("descripcion");
                            auxSonda.leyendaTipoSonda = SondaTipo.getSondaTipoById(auxSonda.tipoSonda).nombre;
                            
                            aux.listaSondas.add(auxSonda);
                        }
                        
                        
                        obj.estacion.add(aux);
                    }
                }

                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).nombre;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).nombre;
                obj.leyendaEdad = Edad.getEdadById(obj.idEdad).nombre;
            }

        });

        return obj;
    }

    public static Lote getLoteById(ObjectId id) {
        Lote obj = new Lote();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("lote").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.idHacienda = (ObjectId) document.get("idhacienda");
                obj.idCultivo = (ObjectId) document.get("idcultivo");
                obj.idVariedad = (ObjectId) document.get("idvariedad");
                obj.idEdad = (ObjectId) document.get("idedad");
                obj.codigo = document.get("codigo").toString();
                obj.hectareas = document.get("hectareas").toString();
                obj.tipoSuelo = document.get("tiposuelo").toString();
                //obj.fecha = document.get("fecha").toString();
                obj.unidad = document.get("unidad").toString();

                List<Document> ests = (List<Document>) document.get("estacion");
                for (int i = 0; i < ests.size(); i++) {

                    List<Document> res = (List<Document>) ests.get(i);
                    for (int j = 0; j < res.size(); j++) {

                        Document dbo = (Document) res.get(j);
                        EstacionMonitoreo aux = new EstacionMonitoreo();
                        aux.codigo = dbo.get("codigo").toString();
                        aux.latitudestacion = dbo.get("latitudestacion").toString();
                        aux.longitudestacion = dbo.get("longitudestacion").toString();
                       
                        List<Document> sondas = (List<Document>) dbo.get("sondas");
                        for(int k=0; k<sondas.size(); k++){
                            Document dboSonda = (Document) sondas.get(k);
                            SondaAux auxSonda = new SondaAux();
                            
                            auxSonda.tipoSonda = (ObjectId) dboSonda.get("tiposonda");
                            auxSonda.descripcion = dboSonda.getString("descripcion");
                            auxSonda.leyendaTipoSonda = SondaTipo.getSondaTipoById(auxSonda.tipoSonda).nombre;
                            
                            aux.listaSondas.add(auxSonda);
                        }

                        obj.estacion.add(aux);
                    }
                }

                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).nombre;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).nombre;
                obj.leyendaEdad = Edad.getEdadById(obj.idEdad).nombre;
            }

        });

        return obj;
    }
    
    
    public static List<Lote> getAllLotesByHaciendaId(ObjectId id) {
        List<Lote> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("lote").find(new Document("idhacienda", id)).sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Lote obj = new Lote();

                obj.id = (ObjectId) document.get("_id");
                obj.idHacienda = (ObjectId) document.get("idhacienda");
                obj.idCultivo = (ObjectId) document.get("idcultivo");
                obj.idVariedad = (ObjectId) document.get("idvariedad");
                obj.idEdad = (ObjectId) document.get("idedad");
                obj.codigo = document.get("codigo").toString();
                obj.hectareas = document.get("hectareas").toString();
                obj.tipoSuelo = document.get("tiposuelo").toString();
                //obj.fecha = document.get("fecha").toString();
                obj.unidad = document.get("unidad").toString();

                List<Document> ests = (List<Document>) document.get("estacion");
                for (int i = 0; i < ests.size(); i++) {

                    List<Document> res = (List<Document>) ests.get(i);
                    for (int j = 0; j < res.size(); j++) {

                        Document dbo = (Document) res.get(j);
                        EstacionMonitoreo aux = new EstacionMonitoreo();
                        aux.codigo = dbo.get("codigo").toString();
                        aux.latitudestacion = dbo.get("latitudestacion").toString();
                        aux.longitudestacion = dbo.get("longitudestacion").toString();
                        
                       
                        List<Document> sondas = (List<Document>) dbo.get("sondas");                       
                        for(int k=0; k<sondas.size(); k++){                        
                            Document dboSonda = (Document) sondas.get(k);
                            SondaAux auxSonda = new SondaAux();
                            
                            auxSonda.tipoSonda = (ObjectId) dboSonda.get("tiposonda");
                            auxSonda.descripcion = dboSonda.getString("descripcion");
                            auxSonda.leyendaTipoSonda = SondaTipo.getSondaTipoById(auxSonda.tipoSonda).nombre;
                            
                            aux.listaSondas.add(auxSonda);
                        }

                        obj.estacion.add(aux);
                    }
                }

                //obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).nombre;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).nombre;
                obj.leyendaEdad = Edad.getEdadById(obj.idEdad).nombre;

                res.add(obj);
            }

        });

        return res;
    }

    public static List<Lote> getAllLotes() {
        List<Lote> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("lote").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Lote obj = new Lote();

                obj.id = (ObjectId) document.get("_id");
                obj.idHacienda = (ObjectId) document.get("idhacienda");
                obj.idCultivo = (ObjectId) document.get("idcultivo");
                obj.idVariedad = (ObjectId) document.get("idvariedad");
                obj.idEdad = (ObjectId) document.get("idedad");
                obj.codigo = document.get("codigo").toString();
                obj.hectareas = document.get("hectareas").toString();
                obj.tipoSuelo = document.get("tiposuelo").toString();
                //obj.fecha = document.get("fecha").toString();
                obj.unidad = document.get("unidad").toString();

                List<Document> ests = (List<Document>) document.get("estacion");
                for (int i = 0; i < ests.size(); i++) {

                    List<Document> res = (List<Document>) ests.get(i);
                    for (int j = 0; j < res.size(); j++) {

                        Document dbo = (Document) res.get(j);
                        EstacionMonitoreo aux = new EstacionMonitoreo();
                        aux.codigo = dbo.get("codigo").toString();
                        aux.latitudestacion = dbo.get("latitudestacion").toString();
                        aux.longitudestacion = dbo.get("longitudestacion").toString();
                        
                        List<Document> sondas = (List<Document>) dbo.get("sondas");
                        for(int k=0; k<sondas.size(); k++){
                            Document dboSonda = (Document) sondas.get(k);
                            SondaAux auxSonda = new SondaAux();
                            
                            auxSonda.tipoSonda = (ObjectId) dboSonda.get("tiposonda");
                            auxSonda.descripcion = dboSonda.getString("descripcion");
                            auxSonda.leyendaTipoSonda = SondaTipo.getSondaTipoById(auxSonda.tipoSonda).nombre;
                            
                            aux.listaSondas.add(auxSonda);
                        }

                        obj.estacion.add(aux);
                    }
                }

                obj.leyendaHacienda = Hacienda.getHaciendaById(obj.idHacienda).nombre;
                obj.leyendaCultivo = Cultivo.getCultivoById(obj.idCultivo).nombre;
                obj.leyendaVariedad = Variedad.getVariedadById(obj.idVariedad).nombre;
                obj.leyendaEdad = Edad.getEdadById(obj.idEdad).nombre;

                res.add(obj);
            }

        });

        return res;
    }

}
